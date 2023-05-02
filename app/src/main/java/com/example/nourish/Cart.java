package com.example.nourish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Cart extends AppCompatActivity {

    TextView textViewCartDate, textDisDur;
    SharedPreferences sharedPreferences;
    Button buttonConfirm, buttonRemove;
    Context context;


    CartDatabase cartDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartDatabase = CartDatabase.getInstance(context);
        setContentView(R.layout.activity_cart);
        textViewCartDate  = findViewById(R.id.textCartData);
        textDisDur = findViewById(R.id.textDisDur);
        buttonConfirm = findViewById(R.id.btnConfirmOrder);
        buttonRemove = findViewById(R.id.btnClearCart);
        sharedPreferences = getSharedPreferences("Customer App", MODE_PRIVATE);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Title", "confirming cart");
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Title", "remove cart");
            }
        });
        generateCart();
    }


    public void generateCart() {

        List<CartItems> items = cartDatabase.cartDoa().getAll();
        for (CartItems item : items) {
            String name = item.getName();
            Log.d("item name", name);
            String price = item.getPrice().toString();
            int numItems = item.getNumItems();
            textViewCartDate.append("\t" + name + "\tPrice £" + price + "\tQuantity: " + numItems + "\n\n");

            // Do something with the name...
        }

    }


}