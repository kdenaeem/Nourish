package com.example.nourish;

import android.util.Log;

public class ListData {
    String id, name, des,img, price;

    public ListData(String id, String name, String des, String img, String price) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.img = img;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getDes() {
        Log.d("tiel", des.toString());
        return des;
    }

    public String getPrice() {
        return price;
    }
}