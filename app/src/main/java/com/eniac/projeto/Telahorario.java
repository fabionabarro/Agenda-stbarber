package com.eniac.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class Telahorario extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);

        final TextView thedate;
        final Button btnconcluir,btnvoltar;
        final Spinner opthorario, optservico;

        thedate = (TextView)findViewById(R.id.data);
        btnconcluir = (Button) findViewById(R.id.btconcluir);
        btnvoltar = (Button) findViewById(R.id.btvoltar);
        opthorario = (Spinner) findViewById(R.id.horario);
        optservico = (Spinner) findViewById(R.id.servico);
        final String HOST = "http://192.168.1.36/login";//pasta local com doc de conexao.php criado no xampp diretorio C:\xampp\htdocs\login

        Intent incomingIntent = getIntent();
        final String data = incomingIntent.getStringExtra("data");
        thedate.setText(data);

        ArrayAdapter horas =ArrayAdapter.createFromResource(this,R.array.lista,R.layout.spinner_item);
        opthorario.setAdapter(horas);

        ArrayAdapter servicos =ArrayAdapter.createFromResource(this,R.array.opt,R.layout.spinner_item);
        optservico.setAdapter(servicos);

        btnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreagenda = new Intent(Telahorario.this, Telaagenda.class);
                startActivity(abreagenda);
                finish();
            }
        });

        btnconcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String servico = optservico.getSelectedItem().toString();
                final String hora = opthorario.getSelectedItem().toString();
                final String data = thedate.getText().toString();



                String URL = HOST + "/agenda.php";

                Ion.with(Telahorario.this)
                        .load(URL)
                        .setBodyParameter("data_app",data )
                        .setBodyParameter("horario_app",hora)
                        .setBodyParameter("servico_app", servico)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                try {

                                    String RETORNO = result.get("AGENDAMENTO").getAsString();

                                    if (RETORNO.equals("AGENDAMENTO_ERRO")){
                                        Toast.makeText(Telahorario.this, "Este horário já esta ocupado. Favor escolher outro horário!" , Toast.LENGTH_LONG).show();

                                    }else if(RETORNO.equals("SUCESSO")){
                                        Toast.makeText(Telahorario.this, "Cadastrado com sucesso!" , Toast.LENGTH_LONG).show();
                                        Intent completa = new Intent(Telahorario.this, Telaresultado.class);
                                        completa.putExtra("data", data);
                                        completa.putExtra("horario", hora);
                                        completa.putExtra("servico", servico);
                                        startActivity(completa);
                                        finish();

                                    }else{
                                        Toast.makeText(Telahorario.this, "Ocorreu um erro!" , Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception erro) {
                                    Toast.makeText(Telahorario.this, "ops deu erro," + erro, Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                    }
                });



            }







}
