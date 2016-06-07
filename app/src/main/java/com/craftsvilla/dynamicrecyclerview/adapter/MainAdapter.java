package com.craftsvilla.dynamicrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.craftsvilla.dynamicrecyclerview.R;
import com.craftsvilla.dynamicrecyclerview.model.mainactivity.SectionList;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naresh on 7/6/16.
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int SINGLE = 1;
    public static final int DOUBLE = 2;
    private static final String TAG = MainAdapter.class.getSimpleName();
    List<SectionList> sectionLists = new ArrayList<>();
    Context context;

    public MainAdapter(Context context, List<SectionList> mainModels) {
        this.context = context;
        this.sectionLists = mainModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == SINGLE) {
            v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.oneimagelayout, parent, false);
            ViewHolderSingle viewHolderSingle = new ViewHolderSingle(v);
            return viewHolderSingle;
        } else {
            v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.twoimagelayout, parent, false);
            ViewHolderDouble viewHolderDouble = new ViewHolderDouble(v);
            return viewHolderDouble;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case SINGLE:
                ViewHolderSingle viewHolderSingle = (ViewHolderSingle) holder;
                if (sectionLists.get(position).getSectionTitle().length() > 0)
                    viewHolderSingle.textViewForOne.setText(sectionLists.get(position).getSectionTitle());
                /*Glide.with(context)
                        .load(sectionLists.get(position).getImageArray().get(0))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolderSingle.imageViewForOne);*/
                Picasso.with(context).load(sectionLists.get(position).getImageArray().get(0))
                        .into(viewHolderSingle.imageViewForOne);
                break;

            case DOUBLE:
                ViewHolderDouble viewHolderDouble = (ViewHolderDouble) holder;
                if (sectionLists.get(position).getSectionTitle().length() > 0)
                    viewHolderDouble.textForTwo.setText(sectionLists.get(position).getSectionTitle());
                /*Glide.with(context)
                        .load(sectionLists.get(position).getImageArray().get(0))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolderDouble.imageForTwo);
                Glide.with(context)
                        .load(sectionLists.get(position).getImageArray().get(1))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolderDouble.imageForTwos);*/
                Picasso.with(context).load(sectionLists.get(position).getImageArray().get(0))
                        .into(viewHolderDouble.imageForTwo);
                Picasso.with(context).load(sectionLists.get(position).getImageArray().get(1))
                        .into(viewHolderDouble.imageForTwos);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return sectionLists.size();
    }

    @Override
    public int getItemViewType(int position) {

        /**
        * This will basically return the size of the image array i.e 1 or 2
        * and according to that the constant is declared with value 1/2
        * and viewType is returned and parse accordingly with inflating the
        * particular view for particular image size with 1 or 2.
        **/

        return sectionLists.get(position).getImageArray().size();
    }

    public class ViewHolderSingle extends RecyclerView.ViewHolder {
        TextView textViewForOne;
        ImageView imageViewForOne;

        public ViewHolderSingle(View itemView) {
            super(itemView);
            textViewForOne = (TextView) itemView.findViewById(R.id.textForOne);
            imageViewForOne = (ImageView) itemView.findViewById(R.id.imageForOne);
        }
    }

    public class ViewHolderDouble extends RecyclerView.ViewHolder {
        TextView textForTwo;
        ImageView imageForTwo, imageForTwos;

        public ViewHolderDouble(View itemView) {
            super(itemView);
            textForTwo = (TextView) itemView.findViewById(R.id.textForTwo);
            imageForTwo = (ImageView) itemView.findViewById(R.id.imageForTwo);
            imageForTwos = (ImageView) itemView.findViewById(R.id.imageForTwos);
        }
    }
}
