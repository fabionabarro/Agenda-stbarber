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

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class Telacadastro extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar);

        final EditText editNome, editEmail2, editSenha2;
        Button btnCancelar, btnRegistrar;
        int codigo;

        final String HOST = "http://192.168.1.36/login";//pasta local com doc de conexao.php criado no xampp diretorio C:\xampp\htdocs\login


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


                String URL = HOST + "/cadastrar.php";//pasta local com doc de cadastrar.php criado no xampp diretorio C:\xampp\htdocs\login

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {

                    Toast.makeText(Telacadastro.this, "Todos os campos são obrigatórios!" , Toast.LENGTH_LONG).show();


                }else {


                    Ion.with(Telacadastro.this)
                            .load(URL)
                            .setBodyParameter("nome_app",nome)
                            .setBodyParameter("email_app",email)
                            .setBodyParameter("senha_app",senha)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    try {

                                        String RETORNO = result.get("CADASTRO").getAsString();

                                        if (RETORNO.equals("EMAIL_ERRO")){
                                            Toast.makeText(Telacadastro.this, "Este email ja esta cadastrado!" , Toast.LENGTH_LONG).show();

                                        }else if(RETORNO.equals("SUCESSO")){
                                            Toast.makeText(Telacadastro.this, "Cadastrado com sucesso!" , Toast.LENGTH_LONG).show();
                                            Intent abrelogin = new Intent(Telacadastro.this, Telalogin.class);
                                            startActivity(abrelogin);
                                        }else{
                                            Toast.makeText(Telacadastro.this, "Ocorreu um erro!" , Toast.LENGTH_LONG).show();
                                        }

                                    } catch (Exception erro) {
                                        Toast.makeText(Telacadastro.this, "ops deu erro," + erro, Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrelogin = new Intent(Telacadastro.this, Telalogin.class);
                startActivity(abrelogin);
                finish();
            }
        });




}

}