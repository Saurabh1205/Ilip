package com.example.ilip.activities.dashboard.otherLinks;

import org.json.JSONArray;

import java.util.ArrayList;

public class OtherLinkCommonModel {
    ArrayList<String> Images;
    String galleryItemName,detailDescription,galleryItemDate_time,newsImage,location;
    JSONArray jsonArrayData;

    public JSONArray getJsonArrayData() {
        return jsonArrayData;
    }

    public void setJsonArrayData(JSONArray jsonArrayData) {
        this.jsonArrayData = jsonArrayData;
    }

    public ArrayList<String> getImages() {
        return Images;
    }

    public void setImages(ArrayList<String> images) {
        Images = images;
    }

    public String getGalleryItemName() {
        return galleryItemName;
    }

    public void setGalleryItemName(String galleryItemName) {
        this.galleryItemName = galleryItemName;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getGalleryItemDate_time() {
        return galleryItemDate_time;
    }

    public void setGalleryItemDate_time(String galleryItemDate_time) {
        this.galleryItemDate_time = galleryItemDate_time;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
