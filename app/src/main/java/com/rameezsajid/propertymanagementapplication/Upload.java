package com.rameezsajid.propertymanagementapplication;

public class Upload {

    private String mName;
    private String mLocation;
    private String mImageUrl;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String location, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        if (location.trim().equals("")){
            location = "No Location";
        }


        mName = name;
        mLocation = location;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLocation(){
        return mLocation;
    }

    public void setLocation(String location){
        mLocation = location;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }


}
