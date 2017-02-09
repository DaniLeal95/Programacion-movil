package com.iesnervion.dleal.appfebrerobar;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.Callbacks.ProductosCallback;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IProductos;
import com.iesnervion.dleal.appfebrerobar.Permisos.Permisos;
import com.iesnervion.dleal.appfebrerobar.Utilidades.BarTrackerDatabaseHelper;
import com.iesnervion.dleal.appfebrerobar.model.Producto;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;

//import okhttp3.ResponseBody;
//import retrofit2.Converter;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;


public class Inicial extends AppCompatActivity implements View.OnClickListener {
    Button pedirmesa, vercarta;

    private Inicial inicial ;
    private SQLiteDatabase db;

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

        this.get("http://dleal.ciclo.iesnervion.es/Producto", new Callback() {

                    @Override
                    public void onFailure(Request request, IOException e) {
                        int i =0;
                        i++;
                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (response.isSuccessful()) {
                            // = response.body();
                            //Log.v("Response",responseStr);

                            //Object object = GsonConverterFactory.create().responseBodyConverter(Producto.class, null, null)
                            //        .convert(ResponseBody.create(MediaType.parse("text"), "null"));
                            List<Producto> productos = (List<Producto>) response.body();

                            BarTrackerDatabaseHelper bbdd = new BarTrackerDatabaseHelper(inicial);
                            bbdd.insertCarta(productos);

                        } else {

                        }
                    }
        });


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
        //String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        String auth ="Basic dXNlcjp1c2Vy";
        return auth;
    }

    //public void getProductosRest() {



    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    Call get(String url, Callback callback) {

        Request request = new Request.Builder()
                .addHeader("Authorization","Basic dXNlcjp1c2Vy")
                .addHeader("Content-Type","application/json")
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

/*
            // code request code here
           public String doGetRequest(String url) throws IOException {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .addHeader("Authorization","Basic dXNlcjp1c2Vy")
                        .addHeader("Content-Type","application/json")
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();
                return response.body().string();
            }

            // post request code here


*/




//        Retrofit retrofit;
//
//        ProductosCallback productoCallback = new ProductosCallback(this);
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://dleal.ciclo.iesnervion.es/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        IProductos productosInter = retrofit.create(IProductos.class);
//        String base64 = codifica64();
//        productosInter.getProductos(base64).enqueue(productoCallback);
    //}



}