package com.example.activitylogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import dao.UserDAO;
import model.User;


public class MainActivity extends AppCompatActivity {
    EditText edtEmail;
    TextView tvCadastrar;
    EditText edtSenha;
    Button btnBotao;
    UserDAO uDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnBotao = findViewById(R.id.btnBotao);


        btnBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getSharedPreferences("appLogin",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email",edtEmail.getText().toString());
                editor.commit();

                uDao = new UserDAO(getApplicationContext(),
                        new User(edtEmail.getText().toString(),
                                edtSenha.getText().toString()));

                if (uDao.verificarEmailESenha()){
                    Intent it = new Intent(MainActivity.this,
                            MainActivityPrincipal.class);
                    // it.putExtra("email", edtEmail.getText().toString());
                    startActivity(it);
                }else{
                    Toast.makeText(MainActivity.this,
                            "Dados Incorretos", Toast.LENGTH_SHORT).show();
                }





            }
        });
        tvCadastrar = findViewById(R.id.tvCadastrar);
        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent it = new Intent(MainActivity.this, Cadastro.class);
                startActivity(it);
            }
        });

;    }
}