package com.craftsvilla.dynamicrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.craftsvilla.dynamicrecyclerview.adapter.MainAdapter;
import com.craftsvilla.dynamicrecyclerview.model.mainactivity.SectionList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<SectionList> sectionLists = new ArrayList<>();
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerMain);
        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        parseJson(loadJsonFromAsset());
    }

    public String loadJsonFromAsset(){
        String json = null;
        InputStream inputStream = null;
        try{
            inputStream = this.getAssets().open("recycler");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    private void parseJson(String json){
        String title;
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArraySectionList = jsonObject.getJSONArray("SectionList");
            for (int i = 0; i < jsonArraySectionList.length(); i++) {
                JSONObject jsonObject1 = jsonArraySectionList.getJSONObject(i);
                title = jsonObject1.getString("sectionTitle");
                JSONArray jsonArrayImage = jsonObject1.getJSONArray("ImageArray");
                List<String> imageArray = new ArrayList<>();
                for (int j = 0; j < jsonArrayImage.length(); j++) {
                    JSONObject jsonObject2 = jsonArrayImage.getJSONObject(j);
                    imageArray.add(jsonObject2.getString("image_url"));
                }
                 sectionLists.add(new SectionList(title,imageArray));
            }
            mainAdapter = new MainAdapter(MainActivity.this,sectionLists);
            recyclerView.setAdapter(mainAdapter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
