package com.rameezsajid.propertymanagementapplication;

public class Properties {

    String propertyID;
    String propertyLocation;
    String propertyType;

    public Properties() {
    }

    public Properties(String propertyID, String propertyLocation, String propertyType) {
        this.propertyID = propertyID;
        this.propertyLocation = propertyLocation;
        this.propertyType = propertyType;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public String getPropertyLocation() {
        return propertyLocation;
    }

    public void setPropertyLocation(String propertyLocation) {
        this.propertyLocation = propertyLocation;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
