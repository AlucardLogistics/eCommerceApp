package com.example.sadic.ecommerceapp.ui.mainfeed;

public class PresenterMain {

    String pId, pName, pQuantity, pPrize, pDescription, pImageUrl;

    public PresenterMain() {
    }

    public PresenterMain(String pId, String pName, String pQuantity,
                         String pPrize, String pDescription, String pImageUrl) {
        this.pId = pId;
        this.pName = pName;
        this.pQuantity = pQuantity;
        this.pPrize = pPrize;
        this.pDescription = pDescription;
        this.pImageUrl = pImageUrl;
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

    public String getpPrize() {
        return pPrize;
    }

    public void setpPrize(String pPrize) {
        this.pPrize = pPrize;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpImageUrl() {
        return pImageUrl;
    }

    public void setpImageUrl(String pImageUrl) {
        this.pImageUrl = pImageUrl;
    }
}
