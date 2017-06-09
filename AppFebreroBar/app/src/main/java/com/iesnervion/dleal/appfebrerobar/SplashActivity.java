package com.iesnervion.dleal.appfebrerobar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.Callbacks.CategoriasCallback;
import com.iesnervion.dleal.appfebrerobar.Callbacks.ProductosCallback;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IBar;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity implements DialogInterface.OnClickListener{

    private SplashActivity main = this;
    private AlertDialog dialog;
    private boolean hayconexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        boolean permisosactivados=false;
        Utilidades u = new Utilidades(main);
        u.borrarProductos();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE}, 1);

            }
        }


        hayconexion= u.hasActiveInternetConnection();
        //Llamada a la api

        if(hayconexion){
            getCategorias();

        }
        else {
            dialog = new AlertDialog.Builder(this)
                  .setTitle("Conexion fallida")
                  .setMessage("Por favor, revisa tu conexion.")
                  .setPositiveButton("Reintentar", this)
                  .create();
                dialog.show();


        }
        //setContentView(R.layout.activity_splash);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Utilidades u = new Utilidades(main);
        hayconexion = u.hasActiveInternetConnection();
        if(!hayconexion) {
            dialog = new AlertDialog.Builder(this)
                    .setTitle("Conexion fallida")
                    .setMessage("Por favor, revisa tu conexion.")
                    .setPositiveButton("Reintentar", this)
                    .create();
            dialog.show();
        }
        else{
            getProductos();
        }
    }

    public String codifica64() {

        String credentials = "user:user";
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        //String auth ="Basic dXNlcjp1c2Vy";
        return auth;
    }

    public void getProductos(){
        Retrofit retrofit;

        ProductosCallback adminCallback = new ProductosCallback(main);



        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.getProductos(base64).enqueue(adminCallback);
    }

    public void getCategorias(){
        Retrofit retrofit;

        CategoriasCallback adminCallback = new CategoriasCallback(main);



        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.getCategoria(base64).enqueue(adminCallback);
    }

    //metodo que será llamado desde el Productoscallback si algo falla
    public void errorProductos(){
        dialog = new AlertDialog.Builder(this)
                .setTitle("Conexion fallida")
                .setMessage("Por favor, revisa tu conexion.")
                .setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        main.getProductos();
                    }
                })
                .create();
        dialog.show();

    }

    //metodo que será llamado desde el Productoscallback si t0do va bien
    public void esperaProductos(){


        Intent i = new Intent(main,Inicial.class);
        startActivity(i);
        finish();
    }
}
