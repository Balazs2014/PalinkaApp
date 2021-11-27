package com.example.plinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class AdatFelvetelActivity extends AppCompatActivity {
    private EditText editTxt_fozo, editTxt_gyumolcs, editTxt_alkoholtartalom;
    private Button btnFelvetel_felvetel, btnFelvetel_vissza;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_felvetel);

        init();

        btnFelvetel_vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(AdatFelvetelActivity.this,
                        MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });

        btnFelvetel_felvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fozo = editTxt_fozo.getText().toString().trim();
                String gyumolcs = editTxt_gyumolcs.getText().toString().trim();
                String alkoholtartalomString = editTxt_alkoholtartalom.getText().toString().trim();

                if (fozo.isEmpty() || gyumolcs.isEmpty() || alkoholtartalomString.isEmpty()) {
                    Toast.makeText(AdatFelvetelActivity.this,
                            "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int alkoholtartalom = Integer.parseInt(alkoholtartalomString);
                        if (alkoholtartalom < 0 || alkoholtartalom > 100) {
                            Toast.makeText(AdatFelvetelActivity.this,
                                    "Az alkoholtartalomnak 0 és 100 közötti  számnak kell lennie",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            if (adatbazis.rogzites(fozo, gyumolcs, alkoholtartalom)) {
                                Toast.makeText(AdatFelvetelActivity.this,
                                        "Sikeres felvétel", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AdatFelvetelActivity.this,
                                        "Sikertelen felvétel", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(AdatFelvetelActivity.this,
                                "Az alkoholtartalomnak számnak kell lennie",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void init() {
        editTxt_fozo = findViewById(R.id.editTxt_fozo);
        editTxt_gyumolcs = findViewById(R.id.editTxt_gyumolcs);
        editTxt_alkoholtartalom = findViewById(R.id.editTxt_alkoholtartalom);
        btnFelvetel_felvetel = findViewById(R.id.btnFelvetel_felvetel);
        btnFelvetel_vissza = findViewById(R.id.btnFelvetel_vissza);
        adatbazis = new DBHelper(this);
    }
}