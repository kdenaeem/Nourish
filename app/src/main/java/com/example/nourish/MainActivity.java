package com.example.nourish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
//import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Button buttonCart, buttonAddress, buttonAllOrders;
    ArrayList<ListData> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("CustomerApp", MODE_PRIVATE);
        buttonCart = findViewById(R.id.btnCart);
        buttonAddress = findViewById(R.id.btnSelectAddress);
        buttonAllOrders = findViewById(R.id.btnAllOrders);
        checkPermissions();
        arrayList = new ArrayList<>();

//        Code for the carts
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
//                getAllCarts();
            }

        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<ListData> listData = new ArrayList<>();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
//                    Log.d("TItle", itemSnapshot.toString());
                    String imgValue = (String) itemSnapshot.child("img").getValue();
                    Log.d("Image Value", imgValue);
                    String id = (String) itemSnapshot.child("id").getValue();
                    String desc = (String) itemSnapshot.child("des").getValue();
                    String name = (String) itemSnapshot.child("name").getValue();
                    String price = (String) itemSnapshot.child("price").getValue();
                    arrayList.add(new ListData(id, name, desc, imgValue, price));

                }
                createRecyclerView();

                // Do something with the list of items
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
    public void getAllCarts() {
        CartDatabase cartDoa = CartDatabase.getInstance(this);
        cartDoa.cartDoa().getAll();
        Log.d("title", cartDoa.cartDoa().getAll().toString());
        Log.d("arrayList", arrayList.toString());
        List<CartItems> cartObjects = new ArrayList<>();
        Random rand = new Random();
        cartDoa.cartDoa().deleteAll();
        for (int x=0;x<arrayList.size(); x++) {

            ListData object = arrayList.get(x);
            String cartItemName = object.getName();
            String cartItemPrice = object.getPrice();
            int uid = rand.nextInt(1000000);
            cartObjects.add(new CartItems(uid, cartItemName, cartItemPrice, 0));
        }
//        Log.d("title", cartObjects.toString());
        for (int x=0;x<cartObjects.size(); x++){
            Log.d("Value of ", String.valueOf(x));
            CartItems item1 = cartObjects.get(x);
            cartDoa.cartDoa().insertAll(cartObjects.get(x));
        }



    }

    public void createRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    public void checkPermissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }
}