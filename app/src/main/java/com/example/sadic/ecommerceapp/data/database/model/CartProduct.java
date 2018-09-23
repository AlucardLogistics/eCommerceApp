package com.example.sadic.ecommerceapp.data.database.model;

public class CartProduct {

    String pId, pName, pQuantity, pPrice, pDescription, pThumbPath;
    int isCart, isWish;

    public CartProduct() {
    }

    public CartProduct(String pId, String pName, String pQuantity,
                       String pPrice, String pDescription, String pThumbPath, int isCart, int isWish) {
        this.pId = pId;
        this.pName = pName;
        this.pQuantity = pQuantity;
        this.pPrice = pPrice;
        this.pDescription = pDescription;
        this.pThumbPath = pThumbPath;
        this.isCart = isCart;
        this.isWish = isWish;
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

    public String getpThumbPath() {
        return pThumbPath;
    }

    public void setpThumbPath(String pThumbPath) {
        this.pThumbPath = pThumbPath;
    }

    public int getIsCart() {
        return isCart;
    }

    public void setIsCart(int isCart) {
        this.isCart = isCart;
    }

    public int getIsWish() {
        return isWish;
    }

    public void setIsWish(int isWish) {
        this.isWish = isWish;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "pId='" + pId + '\'' +
                ", pName='" + pName + '\'' +
                ", pQuantity='" + pQuantity + '\'' +
                ", pPrice='" + pPrice + '\'' +
                ", pDescription='" + pDescription + '\'' +
                ", pThumbPath='" + pThumbPath + '\'' +
                ", isCart=" + isCart +
                ", isWish=" + isWish +
                '}';
    }
}
