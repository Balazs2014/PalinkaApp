package com.example.plinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdatKeresesActivity extends AppCompatActivity {
    private EditText editTxtKereses_fozo, editTxtKereses_gyumolcs;
    private Button btnKereses_kereses, btnKereses_vissza;
    private TextView txtViewKereses_kiiras;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_kereses);

        init();

        btnKereses_vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(AdatKeresesActivity.this,
                        MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });

        btnKereses_kereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fozo = editTxtKereses_fozo.getText().toString().trim();
                String gyumolcs = editTxtKereses_gyumolcs.getText().toString().trim();
                Cursor adat = adatbazis.kereses(fozo, gyumolcs);
                if (fozo.isEmpty() || gyumolcs.isEmpty()) {
                    Toast.makeText(AdatKeresesActivity.this,
                            "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
                } else {
                    if (adat.getCount() == 0) {
                        txtViewKereses_kiiras.setText("A megadott adatokkal nem található pálinka");
                    } else {
                        StringBuilder sb = new StringBuilder();
                        while (adat.moveToNext()) {
                            sb.append("Alkoholtartalom: ").append(adat.getInt(0));
                            sb.append(" %");
                        }
                        txtViewKereses_kiiras.setText(sb.toString());
                    }
                }
            }
        });
    }

    private void init() {
        editTxtKereses_fozo = findViewById(R.id.editTxtKereses_fozo);
        editTxtKereses_gyumolcs = findViewById(R.id.editTxtKereses_gyumolcs);
        txtViewKereses_kiiras = findViewById(R.id.txtViewKereses_kiiras);
        btnKereses_kereses = findViewById(R.id.btnKereses_kereses);
        btnKereses_vissza = findViewById(R.id.btnKereses_vissza);
        adatbazis = new DBHelper(this);
    }
}