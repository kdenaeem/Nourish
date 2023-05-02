package com.example.nourish;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
interface CartDoa{
    @Query("SELECT * FROM cartTable")
    List<CartItems> getAll();

    @Insert
    void insertAll(CartItems... items);

    @Delete
    void delete(CartItems items);

    @Query("DELETE FROM cartTable")
    void deleteAll();

    @Query("UPDATE cartTable SET numItems = numItems + 1 WHERE name = :name")
    void incrementNumItems(String name);

    @Query("UPDATE cartTable SET numItems = numItems - 1 WHERE name = :name")
    void decrementNumItems(String name);

    @Query("SELECT name FROM cartTable")
    List<String> getAllItemNames();


}
