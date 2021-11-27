package com.example.plinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AdatFelvetelActivity extends AppCompatActivity {
    private EditText editTxt_fozo, editTxt_gyumolcs, editTxt_alkoholtartalom;
    private Button btnFelvetel_felvetel, btnFelvetel_vissza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_felvetel);

        init();
    }

    private void init() {
        editTxt_fozo = findViewById(R.id.editTxt_fozo);
        editTxt_gyumolcs = findViewById(R.id.editTxt_gyumolcs);
        editTxt_alkoholtartalom = findViewById(R.id.editTxt_alkoholtartalom);
        btnFelvetel_felvetel = findViewById(R.id.btnFelvetel_felvetel);
        btnFelvetel_vissza = findViewById(R.id.btnFelvetel_vissza);
    }
}