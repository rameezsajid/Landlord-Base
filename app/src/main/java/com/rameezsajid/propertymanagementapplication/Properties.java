package com.rameezsajid.propertymanagementapplication;

public class Properties {

    String propertyID;
    String propertyLocation;
    String propertyAddress;
    String propertyPostcode;
    String propertyType;
    String propertyRental;
    String propertyTenancyLength;
    String propertyTenantName;
    String propertyManagementType;
    String propertyManagementName;

    public Properties() {
    }

    public Properties(String propertyID, String propertyLocation, String propertyAddress, String propertyPostcode, String propertyType, String propertyRental, String propertyTenancyLength, String propertyTenantName, String propertyManagementType, String propertyManagementName) {
        this.propertyID = propertyID;
        this.propertyLocation = propertyLocation;
        this.propertyAddress = propertyAddress;
        this.propertyPostcode = propertyPostcode;
        this.propertyType = propertyType;
        this.propertyRental = propertyRental;
        this.propertyTenancyLength = propertyTenancyLength;
        this.propertyTenantName = propertyTenantName;
        this.propertyManagementType = propertyManagementType;
        this.propertyManagementName = propertyManagementName;
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

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getPropertyPostcode() {
        return propertyPostcode;
    }

    public void setPropertyPostcode(String propertyPostcode) {
        this.propertyPostcode = propertyPostcode;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyRental() {
        return propertyRental;
    }

    public void setPropertyRental(String propertyRental) {
        this.propertyRental = propertyRental;
    }

    public String getPropertyTenancyLength() {
        return propertyTenancyLength;
    }

    public void setPropertyTenancyLength(String propertyTenancyLength) {
        this.propertyTenancyLength = propertyTenancyLength;
    }

    public String getPropertyTenantName() {
        return propertyTenantName;
    }

    public void setPropertyTenantName(String propertyTenantName) {
        this.propertyTenantName = propertyTenantName;
    }

    public String getPropertyManagementType() {
        return propertyManagementType;
    }

    public void setPropertyManagementType(String propertyManagementType) {
        this.propertyManagementType = propertyManagementType;
    }

    public String getPropertyManagementName() {
        return propertyManagementName;
    }

    public void setPropertyManagementName(String propertyManagementName) {
        this.propertyManagementName = propertyManagementName;
    }
}
