package com.iesnervion.dleal.appfebrerobar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicial = this;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.INTERNET}, 1);

            }
        }

        //TODO: CAMBIAR ESTO POR LA LLAMADA A LA API


        getProductos();



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
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                break;
            case R.id.btnverCarta:
                i = new Intent(this, Carta.class);
        }

        startActivity(i);
    }


    public String codifica64() {

        String credentials = "user:user";
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        //String auth ="Basic dXNlcjp1c2Vy";
        return auth;
    }

    public void getProductos(){
        Retrofit retrofit;

        ProductosCallback adminCallback = new ProductosCallback(this);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.getProductos(base64).enqueue(adminCallback);
    }






}