package com.example.nourish;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.util.List;

import javax.xml.namespace.QName;

@Entity(tableName = "cartTable")
public class CartItems {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name="price")
    public String price;

    @ColumnInfo(name="numItems")
    public int numItems;

    public CartItems(int uid, String name, String price, int numItems) {
        this.uid = uid;
        this.name = name;
        this.price = price;
        this.numItems = numItems;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getNumItems() {
        return numItems;
    }
}
