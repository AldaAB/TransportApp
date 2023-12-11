package com.example.transportappv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChoferActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chofer1);

    navegar();
    }

    private void navegar(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment2);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}