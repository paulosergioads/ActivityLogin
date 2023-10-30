package com.example.activitylogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityPrincipal extends AppCompatActivity {

    TextView textEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);


        textEmail = findViewById(R.id.textEmail);

        SharedPreferences sp = getSharedPreferences("appLogin",
                Context.MODE_PRIVATE);

        String email = sp.getString("email","abc");

        textEmail.setText(email);
    }
}