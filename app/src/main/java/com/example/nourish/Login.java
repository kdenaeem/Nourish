package com.example.nourish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    String name, email, password;
    TextView textViewError, textViewRegister;
    Button buttonSubmit;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonSubmit = findViewById(R.id.submit);
        progressBar = findViewById(R.id.loading);
        sharedPreferences = getSharedPreferences("Customer Application", MODE_PRIVATE);
        textViewError = findViewById(R.id.error);
        textViewRegister = findViewById(R.id.registerNow);


        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                finish();
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                textViewError.setVisibility(View.GONE);
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                if(email.equals("") && password.equals("")) {
                    Toast.makeText(Login.this, "All Fields are required", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Login.this, "Account Created", Toast.LENGTH_SHORT).show();
//                    Editor object
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("login", "true");
                    myEdit.putString("name", "admin");
                    myEdit.putString("email", "email@1");
                    myEdit.apply();
//                    main activity.
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}