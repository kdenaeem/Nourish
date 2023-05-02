package com.example.nourish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Registration extends AppCompatActivity {


//  Each object is used to edit a different field
    TextInputEditText editTextName, editTextEmail, editTextPassword;
    String name, email, password;
    TextView textViewError, textViewLogin;
    Button buttonSubmit;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        textViewError = findViewById(R.id.error);
        textViewLogin = findViewById(R.id.loginNow);
        buttonSubmit = findViewById(R.id.submit);
        progressBar = findViewById(R.id.loading);

//        Listener for Login now button which switches to Login page
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

//        Listener for registration.
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                if(name.equals("") && email.equals("") && password.equals("")) {
                    Toast.makeText(Registration.this, "All Fields are required", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Registration.this, "Account Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });


    }
}