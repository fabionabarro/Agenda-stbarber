package com.eniac.projeto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Telacadastro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar);

        final EditText editNome, editEmail2, editSenha2;
        Button btnCancelar, btnRegistrar;

        final String HOST = "http://192.168.1.36/login";

        editNome = (EditText )findViewById(R.id.editNome);
        editEmail2 = (EditText )findViewById(R.id.editEmail2);
        editSenha2 = (EditText )findViewById(R.id.editSenha2);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = editNome.getText().toString();
                String email = editEmail2.getText().toString();
                String senha = editSenha2.getText().toString();


                String URL = HOST + "/cadastrar.php";

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
                                        //Toast.makeText(Telacadastro.this, "Retorno" + result.toString(), Toast.LENGTH_LONG).show();
                                        String RETORNO = result.get("CADASTRO").getAsString();

                                        if (RETORNO.equals("EMAIL_ERRO")){
                                            Toast.makeText(Telacadastro.this, "Este email ja esta cadastrado!" , Toast.LENGTH_LONG).show();

                                        }else if(RETORNO.equals("SUCESSO")){
                                            Toast.makeText(Telacadastro.this, "Cadastrado com sucesso!" , Toast.LENGTH_LONG).show();
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
                finish();
            }
        });



    }
}

