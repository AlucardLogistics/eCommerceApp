package com.example.sadic.ecommerceapp.data.network.model;

public class Product {

    String pId, pName, pQuantity, pPrice, pDescription, pThumbUrl;

    public Product() {
    }

    public Product(String pId, String pName,
                   String pQuantity, String pPrice, String pDescription, String pThumbUrl) {
        this.pId = pId;
        this.pName = pName;
        this.pQuantity = pQuantity;
        this.pPrice = pPrice;
        this.pDescription = pDescription;
        this.pThumbUrl = pThumbUrl;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(String pQuantity) {
        this.pQuantity = pQuantity;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpThumbUrl() {
        return pThumbUrl;
    }

    public void setpThumbUrl(String pThumbUrl) {
        this.pThumbUrl = pThumbUrl;
    }
}
