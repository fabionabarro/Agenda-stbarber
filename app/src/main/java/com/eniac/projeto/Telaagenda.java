package com.eniac.projeto;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;


public class Telaagenda extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda);

        Button btnVoltar , btnAgendado;
        CalendarView calendar;

        btnVoltar = (Button) findViewById(R.id.voltar);

        calendar = (CalendarView) findViewById(R.id.calendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = (dayOfMonth)+"/"+(month + 1) + "/" + (year);

                Intent intent = new Intent(Telaagenda.this,Telahorario.class);
                intent.putExtra("data",date);
                startActivity(intent);
                finish();


            }
        });



        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreinicial = new Intent(Telaagenda.this, Telainicial.class);
                startActivity(abreinicial);
                finish();

            }
        });




    }
}