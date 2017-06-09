package com.iesnervion.dleal.appfebrerobar.Callbacks;

import com.iesnervion.dleal.appfebrerobar.SplashActivity;
import com.iesnervion.dleal.appfebrerobar.Utilidades.BarTrackerDatabaseHelper;
import com.iesnervion.dleal.appfebrerobar.model.Categoria;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by danie on 28/05/2017.
 */

public class CategoriasCallback implements Callback<List<Categoria>> {


    private SplashActivity main;

    public CategoriasCallback(SplashActivity main) {
        this.main = main;
    }

    @Override
    public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {


        BarTrackerDatabaseHelper bbdd = new BarTrackerDatabaseHelper(main);
        bbdd.insertCategorias(response.body());
        main.getProductos();
    }



    @Override
    public void onFailure(Call<List<Categoria>> call, Throwable t) {

        main.errorProductos();
    }
}
