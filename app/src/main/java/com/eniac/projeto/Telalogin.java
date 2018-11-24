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
        Button btnLogar, btnCadastro;
       final String HOST = "http://192.168.1.36/login";

        editEmail1 = (EditText )findViewById(R.id.EDlogin);
        editSenha1 = (EditText )findViewById(R.id.EDsenha);
        btnLogar = (Button)findViewById(R.id.btnLogar);
        btnCadastro = (Button)findViewById(R.id.btnCadastro);



        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmail1.getText().toString();
                String senha = editSenha1.getText().toString();

                String URL = HOST + "/logar.php";

                if (email.isEmpty() || senha.isEmpty()) {

                    Toast.makeText(Telalogin.this, "Todos os campos são obrigatórios!" , Toast.LENGTH_LONG).show();


                }else {


                    Ion.with(Telalogin.this)
                            .load(URL)

                            .setBodyParameter("email_app",email)
                            .setBodyParameter("senha_app",senha)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    try {

                                        String RETORNO = result.get("LOGIN").getAsString();

                                        if (RETORNO.equals("ERRO")){
                                            Toast.makeText(Telalogin.this, "Email ou senha incorretos" , Toast.LENGTH_LONG).show();

                                        }else if(RETORNO.equals("SUCESSO")){
                                            Intent abreTelaInicial = new Intent(Telalogin.this, Telainicial.class);
                                            startActivity(abreTelaInicial);
                                        }else{
                                            Toast.makeText(Telalogin.this, "Ocorreu um erro!" , Toast.LENGTH_LONG).show();
                                        }

                                    } catch (Exception erro) {
                                        Toast.makeText(Telalogin.this, "ops deu erro," + erro, Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                }



            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastro = new Intent(Telalogin.this, Telacadastro.class);
                startActivity(abreCadastro);
            }
        });

    }
}

