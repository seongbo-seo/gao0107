package com.example.bottomnavigationwithfragment;

public class StoreItem {

    String storeImage;
    String storeName;
    String storeTime;
    String storeToday;


    public StoreItem(String storeImage, String storeName, String storeTime, String storeToday) {
        this.storeImage = storeImage;
        this.storeName = storeName;
        this.storeTime = storeTime;
        this.storeToday = storeToday;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }

    public String getStoreToday() {
        return storeToday;
    }

    public void setStoreToday(String storeToday) {
        this.storeToday = storeToday;
    }

    @Override
    public String toString() {
        return "StoreItem{" +
                "storeImage='" + storeImage + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeTime='" + storeTime + '\'' +
                ", storeToday='" + storeToday + '\'' +
                '}';
    }
}
