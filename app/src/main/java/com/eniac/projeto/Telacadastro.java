package com.eniac.projeto;

import android.support.v7.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import android.app.Notification;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;



public class Telacadastro extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar);

        final EditText editNome, editEmail2, editSenha2;
        Button btnCancelar, btnRegistrar;
        int codigo;


        editNome = (EditText) findViewById(R.id.editNome);
        editEmail2 = findViewById(R.id.editEmail2);
        editSenha2 = findViewById(R.id.editSenha2);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmail2.getText().toString();
                String senha = editSenha2.getText().toString();
                String nome = editNome.getText().toString();

                if (email.isEmpty() || senha.isEmpty() || nome.isEmpty()) {

                    Toast.makeText(Telacadastro.this, "Todos os campos são obrigatórios!" , Toast.LENGTH_LONG).show();


                }else {
                    Toast.makeText(Telacadastro.this, "Registrado com sucesso!" , Toast.LENGTH_LONG).show();

                    Intent abretelainicial = new Intent(Telacadastro.this, Telalogin.class);
                    startActivity(abretelainicial);
                    finish();
                }



            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




}

}