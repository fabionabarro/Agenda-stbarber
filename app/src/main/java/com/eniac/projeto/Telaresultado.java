package com.eniac.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Telaresultado extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        TextView tspinner, tlista,tdate;
        Button btnfinalizar;
        final String HOST = "http://192.168.1.36/login";//pasta local com doc de conexao.php criado no xampp diretorio C:\xampp\htdocs\login

        tspinner = (TextView) findViewById(R.id.textspinner);
        tlista = (TextView)  findViewById(R.id.textlista);
        tdate = (TextView)  findViewById(R.id.textdate);
        btnfinalizar = (Button) findViewById(R.id.btnfinalizar);

        Intent incomingIntent = getIntent();
        String servico = incomingIntent.getStringExtra("servico");
        String hora = incomingIntent.getStringExtra("horario");
        String date = incomingIntent.getStringExtra("data");
        tspinner.setText(hora);
        tlista.setText(servico);
        tdate.setText(date);

        btnfinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreagenda = new Intent(Telaresultado.this, Telainicial.class);
                startActivity(abreagenda);
                finish();
            }
        });

    }

}