package com.example.transportappv3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class CodigoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);

        // Cambiar el título de la ActionBar y centrarlo
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Pago con QR");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            // Configurar el título para que aparezca centrado
            final TextView actionBarTitle = new TextView(this);
            actionBarTitle.setText("Pago con QR");
            actionBarTitle.setTextColor(Color.WHITE);
            actionBarTitle.setTextSize(30); // Tamaño del texto
            actionBarTitle.setGravity(Gravity.CENTER);

            // Establecer el título personalizado en la ActionBar
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(actionBarTitle, new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER));
        }
    }
    }