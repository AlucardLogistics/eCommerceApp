package com.example.sadic.ecommerceapp.data.network.model;

public class Category {
    String cId, cName, cDescription, cImage;

    public Category() {
    }

    public Category(String cId, String cName, String cDescription, String cImage) {
        this.cId = cId;
        this.cName = cName;
        this.cDescription = cDescription;
        this.cImage = cImage;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }
}
