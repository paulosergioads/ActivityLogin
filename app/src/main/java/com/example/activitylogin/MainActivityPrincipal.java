package com.example.activitylogin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dao.UserDAO;
import model.User;

public class MainActivityPrincipal extends AppCompatActivity {

    EditText textEmail;
    EditText textView;
    UserDAO vDao;
    Button btnSair;
    Button btnAtualizar;
    Button btnExcluir;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);


        textEmail = findViewById(R.id.textEmail);
        textView = findViewById(R.id.textView);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnExcluir = findViewById(R.id.btnExcluir);



        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getSharedPreferences("appLogin",
                        Context.MODE_PRIVATE);

                String email = sp.getString("email","abc");
                String nome = sp.getString("nome","abc");

                textEmail.setText(email);
                textView.setText(nome);

                vDao = new UserDAO(getApplication(),
                        new User(
                                textEmail.getText().toString(),
                                textView.getText().toString()
                        ));


                if (vDao.atualizar()){
                    Toast.makeText(MainActivityPrincipal.this, "ATUALIZADO", Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText(MainActivityPrincipal.this, "ERRO", Toast.LENGTH_SHORT ).show();
                }



            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getSharedPreferences("appLogin",
                        Context.MODE_PRIVATE);

                String email = sp.getString("email","abc");
                String nome = sp.getString("nome","abc");

                textEmail.setText(email);
                textView.setText(nome);

                vDao = new UserDAO(getApplication(),
                        new User(
                                textEmail.getText().toString(),
                                textView.getText().toString()
                        ));


                if (vDao.delete()){
                    Toast.makeText(MainActivityPrincipal.this, "ATUALIZADO", Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText(MainActivityPrincipal.this, "ERRO", Toast.LENGTH_SHORT ).show();
                }


            }
        });


        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it =  new Intent(MainActivityPrincipal.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });


    }
}

