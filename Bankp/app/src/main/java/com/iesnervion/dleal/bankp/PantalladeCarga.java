package com.iesnervion.dleal.bankp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantalladeCarga extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallade_carga);
        getSupportActionBar().hide();



        new CountDownTimer(5000,5000){
            @Override
            public void onFinish() {
                Intent cambioPantallaLogin=new Intent(PantalladeCarga.this,PantallaLogin.class);

                PantalladeCarga.this.startActivity(cambioPantallaLogin);
            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();


    }



}
