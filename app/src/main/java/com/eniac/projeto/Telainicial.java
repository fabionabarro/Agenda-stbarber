package com.eniac.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Telainicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bemvindo);

        Button btnagendar, btnsair;

        btnagendar = (Button) findViewById(R.id.agendar);
        btnsair = (Button) findViewById(R.id.sair);

        btnsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnagendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreagenda = new Intent(Telainicial.this, Telaagenda.class);
                startActivity(abreagenda);
                finish();
            }
        });




    }
}
