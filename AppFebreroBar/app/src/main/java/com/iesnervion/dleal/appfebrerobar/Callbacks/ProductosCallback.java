package com.iesnervion.dleal.appfebrerobar.Callbacks;

import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.Inicial;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IProductos;
import com.iesnervion.dleal.appfebrerobar.Utilidades.BarTrackerDatabaseHelper;
import com.iesnervion.dleal.appfebrerobar.model.Producto;


import java.util.List;


//import retrofit2.Call;
//import retrofit2.Callback;
//
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by dleal on 9/02/17.
 */

public class ProductosCallback /*implements Callback<List<Producto>> */{

/*
    private Inicial main;

    public ProductosCallback(Inicial main) {
        this.main = main;
    }

    @Override
    public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {


        BarTrackerDatabaseHelper bbdd = new BarTrackerDatabaseHelper(main);
        bbdd.insertCarta(response.body());
    }

    @Override
    public void onFailure(Call<List<Producto>> call, Throwable t) {

        if(t.toString().contains("java.io.EOFException")){
            Retrofit retrofit;

            ProductosCallback productoCallback = new ProductosCallback(main);

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://dleal.ciclo.iesnervion.es/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            IProductos productosInter = retrofit.create(IProductos.class);
            String base64 = "Basic dXNlcjp1c2Vy";
            productosInter.getProductos(base64).enqueue(productoCallback);
        }else{
            Toast.makeText(main,t.toString(),Toast.LENGTH_SHORT).show();
        }

    }*/

}