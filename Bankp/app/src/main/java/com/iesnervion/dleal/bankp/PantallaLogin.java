package com.iesnervion.dleal.bankp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PantallaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_login);
        getSupportActionBar().hide();
    }
}
