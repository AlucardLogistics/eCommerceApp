package com.example.sadic.ecommerceapp.data.network.model;

public class SubCategory {

    String scId, scName, scDescription, scImage;

    public SubCategory() {
    }

    public SubCategory(String scId, String scName, String scDescription, String scImage) {
        this.scId = scId;
        this.scName = scName;
        this.scDescription = scDescription;
        this.scImage = scImage;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public String getScDescription() {
        return scDescription;
    }

    public void setScDescription(String scDescription) {
        this.scDescription = scDescription;
    }

    public String getScImage() {
        return scImage;
    }

    public void setScImage(String scImage) {
        this.scImage = scImage;
    }
}
