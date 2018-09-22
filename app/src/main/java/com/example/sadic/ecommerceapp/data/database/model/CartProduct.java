package com.example.sadic.ecommerceapp.data.database.model;

public class CartProduct {

    String pId, pName, pQuantity, pPrice, pThumbPath;
    boolean isCart, isWish;

    public CartProduct() {
    }

    public CartProduct(String pId, String pName, String pQuantity,
                       String pPrice, String pThumbPath, boolean isCart, boolean isWish) {
        this.pId = pId;
        this.pName = pName;
        this.pQuantity = pQuantity;
        this.pPrice = pPrice;
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

    public String getpThumbPath() {
        return pThumbPath;
    }

    public void setpThumbPath(String pThumbPath) {
        this.pThumbPath = pThumbPath;
    }

    public boolean isCart() {
        return isCart;
    }

    public void setCart(boolean cart) {
        isCart = cart;
    }

    public boolean isWish() {
        return isWish;
    }

    public void setWish(boolean wish) {
        isWish = wish;
    }
}
