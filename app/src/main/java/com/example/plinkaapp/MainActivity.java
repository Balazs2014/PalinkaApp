package com.example.plinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn_felvetel, btn_kereses, btn_listazas;
    private TextView txtView_lista;

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
    }

    private void init() {
        btn_felvetel = findViewById(R.id.btn_felvetel);
        btn_kereses = findViewById(R.id.btn_kereses);
        btn_listazas = findViewById(R.id.btn_listazas);
        txtView_lista = findViewById(R.id.txtView_lista);
    }
}