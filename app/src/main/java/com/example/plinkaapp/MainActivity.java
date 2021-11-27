package com.example.plinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn_felvetel, btn_kereses, btn_listazas;
    private TextView txtView_lista;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        txtView_lista.setMovementMethod(new ScrollingMovementMethod());

        btn_felvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent felvetel = new Intent(MainActivity.this, AdatFelvetelActivity.class);
                startActivity(felvetel);
                finish();
            }
        });

        btn_kereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kereses = new Intent(MainActivity.this, AdatKeresesActivity.class);
                startActivity(kereses);
                finish();
            }
        });

        btn_listazas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor adatok = adatbazis.listazas();
                if (adatok.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Nincs adat az adatbázisban", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuilder sb = new StringBuilder();
                    while (adatok.moveToNext()) {
                        sb.append("ID: ").append(adatok.getInt(0));
                        sb.append(System.lineSeparator());
                        sb.append("Főző: ").append(adatok.getString(1));
                        sb.append(System.lineSeparator());
                        sb.append("Gyümölcs: ").append(adatok.getString(2));
                        sb.append(System.lineSeparator());
                        sb.append("Alkoholtartalom: ").append(adatok.getInt(3));
                        sb.append(System.lineSeparator());
                        sb.append(System.lineSeparator());
                    }
                    txtView_lista.setText(sb.toString());
                }
            }
        });
    }

    private void init() {
        btn_felvetel = findViewById(R.id.btn_felvetel);
        btn_kereses = findViewById(R.id.btn_kereses);
        btn_listazas = findViewById(R.id.btn_listazas);
        txtView_lista = findViewById(R.id.txtView_lista);
        adatbazis = new DBHelper(this);
    }
}