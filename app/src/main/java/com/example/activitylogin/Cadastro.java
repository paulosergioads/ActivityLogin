package com.example.activitylogin;

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


public class Cadastro extends AppCompatActivity {

    EditText edtNome2;
    EditText edtEmail2;
    EditText edtSenha2;
    UserDAO vDao;
    Button btnBotao2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome2 = findViewById(R.id.edtNome2);
        edtEmail2 = findViewById(R.id.edtEmail2);
        edtSenha2 = findViewById(R.id.edtSenha2);
        btnBotao2 = findViewById(R.id.btnBotao2);

        btnBotao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail2.getText().toString().isEmpty()){
                    edtEmail2.setError("Campo Obrigadorio");
                }
                if(edtNome2.getText().toString().isEmpty()){
                    edtNome2.setError("Campo Obrigadorio");
                }

                if(edtSenha2.getText().toString().isEmpty()){
                    edtSenha2.setError("Campo Obrigadorio");
                }

                SharedPreferences sp = getSharedPreferences("appLogin",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email",edtEmail2.getText().toString());
                editor.commit();

                vDao = new UserDAO(getApplication(),
                        new User(
                        edtEmail2.getText().toString(),
                        edtNome2.getText().toString(),
                        edtSenha2.getText().toString()
                ));


                if (vDao.cadastrar()){
                    Intent it = new Intent(Cadastro.this, MainActivityPrincipal.class);
                    startActivity(it);

                }else{
                    Toast.makeText(Cadastro.this, "ERRO", Toast.LENGTH_SHORT ).show();
                }


        }


        });


    }
}