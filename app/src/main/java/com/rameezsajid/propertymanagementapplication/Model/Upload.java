package com.rameezsajid.propertymanagementapplication.Model;

public class Upload {

    private String mName;
    private String mLocation;
    private String mAddress;
    private String mPostcode;
    private String mBedroom;
    private String mPropertyType;
    private String mRent;
    private String mEmail;
    private String mPhone;
    private String mImageUrl;

    public Upload() {
        //empty constructor needed
    }

    public Upload(String name, String location, String address, String postcode, String bedroom, String type, String rent, String email, String phone, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        if (location.trim().equals("")){
            location = "No Location";
        }


        mName = name;
        mLocation = location;
        mAddress = address;
        mPostcode = postcode;
        mBedroom = bedroom;
        mPropertyType = type;
        mRent = rent;
        mEmail = email;
        mPhone = phone;
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

    public String getAddress(){
        return mAddress;
    }

    public void setAddress(String address){
        mAddress = address;
    }

    public String getPostcode(){
        return mPostcode;
    }

    public void setPostcode(String postcode){
        mPostcode = postcode;
    }

    public String getBedroom(){
        return mBedroom;
    }

    public void setBedroom(String bedroom){
        mBedroom = bedroom;
    }

    public String getPropertyType(){
        return mPropertyType;
    }

    public void setPropertyType(String type){
        mPropertyType = type;
    }

    public String getRent(){
        return mRent;
    }

    public void setRent(String rent){
        mRent = rent;
    }

    public String getEmail(){
        return mEmail;
    }

    public void setEmail(String email){
        mEmail = email;
    }

    public String getPhone(){
        return mPhone;
    }

    public void setPhone(String phone){
        mPhone = phone;
    }


    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }


}
