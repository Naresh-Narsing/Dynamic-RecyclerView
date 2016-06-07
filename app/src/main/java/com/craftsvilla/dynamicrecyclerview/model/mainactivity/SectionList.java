package com.craftsvilla.dynamicrecyclerview.model.mainactivity;

/**
 * Created by naresh on 7/6/16.
 */

import java.util.ArrayList;
import java.util.List;


public class SectionList {

    private String sectionTitle;
    private List<String> imageArray = new ArrayList<String>();

    public SectionList(String sectionTitle, List<String> imageArray) {
        this.sectionTitle = sectionTitle;
        this.imageArray = imageArray;
    }

    /**
     * @return The sectionTitle
     */
    public String getSectionTitle() {
        return sectionTitle;
    }

    /**
     * @param sectionTitle The sectionTitle
     */
    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    /**
     * @return The imageArray
     */
    public List<String> getImageArray() {
        return imageArray;
    }

    /**
     * @param imageArray The ImageArray
     */
    public void setImageArray(List<String> imageArray) {
        this.imageArray = imageArray;
    }
}