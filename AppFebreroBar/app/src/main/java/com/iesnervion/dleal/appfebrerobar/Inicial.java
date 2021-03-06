package com.iesnervion.dleal.appfebrerobar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iesnervion.dleal.appfebrerobar.Callbacks.ProductosCallback;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IBar;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Inicial extends AppCompatActivity implements View.OnClickListener {
    Button pedirmesa, vercarta;

    private Inicial inicial ;
    private SQLiteDatabase db;
    private List<Producto> productos;

    private Inicial activity=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicial = this;

        Intent i= getIntent();

        Bundle bundle= i.getExtras();

        if(bundle != null) {
            if (bundle.getBoolean("cerrar")) {

                //  super.onDestroy();

                // moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                //System.exit(1);
            }
        }

        setContentView(R.layout.activity_inicial);

        pedirmesa = (Button) findViewById(R.id.btnpedirmesa);
        vercarta = (Button) findViewById(R.id.btnverCarta);

        pedirmesa.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/EraserDust.ttf"));
        vercarta.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/EraserDust.ttf"));

        pedirmesa.setOnClickListener(this);
        vercarta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.btnpedirmesa:
                i = new Intent(this, LoginMesa.class);


                break;
            case R.id.btnverCarta:
                i = new Intent(this, Carta.class);
        }

        startActivity(i);
    }


    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }





}