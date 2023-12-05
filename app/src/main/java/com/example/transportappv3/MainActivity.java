package com.example.transportappv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Entrar(View view) {
        Intent intent = new Intent(view.getContext(), Principal1.class);
        view.getContext().startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void entrarRegistro(View view) {
        Intent intent = new Intent(view.getContext(), RegistroActivity.class);
        view.getContext().startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}