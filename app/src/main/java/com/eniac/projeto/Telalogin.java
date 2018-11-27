package com.eniac.projeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Telalogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText editEmail1, editSenha1;
        final Button btnLogar, btnCadastro, btnSair;



        editEmail1 = findViewById(R.id.EDlogin);
        editSenha1 = findViewById(R.id.EDsenha);
        btnLogar = findViewById(R.id.btnLogar);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnSair = findViewById(R.id.btnSair);



        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmail1.getText().toString();
                String senha = editSenha1.getText().toString();



                if (email.isEmpty() || senha.isEmpty()) {

                    Toast.makeText(Telalogin.this, "Todos os campos são obrigatórios!" , Toast.LENGTH_LONG).show();


                }else {
                    Intent abretelainicial = new Intent(Telalogin.this, Telainicial.class);
                    startActivity(abretelainicial);
                    finish();
                }



            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(Telalogin.this, Telacadastro.class);
                startActivity(abreCadastro);
                finish();
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}

