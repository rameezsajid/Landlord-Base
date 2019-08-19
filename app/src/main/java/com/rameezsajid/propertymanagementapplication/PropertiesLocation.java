package com.rameezsajid.propertymanagementapplication;

public class PropertiesLocation {

    String propertyID;
    String propertyLocation;

    public PropertiesLocation() {
    }

    public PropertiesLocation(String propertyID, String propertyLocation) {
        this.propertyID = propertyID;
        this.propertyLocation = propertyLocation;
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
}
