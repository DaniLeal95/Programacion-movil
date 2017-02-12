package com.iesnervion.dleal.appfebrerobar.Callbacks;

import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.LoginMesa;
import com.iesnervion.dleal.appfebrerobar.Utilidades.BarTrackerDatabaseHelper;
import com.iesnervion.dleal.appfebrerobar.model.Mesa;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dani on 11/02/2017.
 */

public class MesasCallback implements Callback<List<Mesa>> {

    private LoginMesa main;

    public MesasCallback(LoginMesa main) {
        this.main = main;
    }

    @Override
    public void onResponse(Call<List<Mesa>> call, Response<List<Mesa>> response) {
        List<Mesa> mesas = response.body();
        main.obtieneMesas(mesas);
    }

    @Override
    public void onFailure(Call<List<Mesa>> call, Throwable t) {



        Toast.makeText(main,t.toString(),Toast.LENGTH_SHORT).show();


    }
}
