package com.iesnervion.dleal.appfebrerobar.Callbacks;

import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.LoginMesa;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Dani on 12/02/2017.
 */

public class CuentaCallback implements Callback <List<Cuenta>> {

    private LoginMesa main;

    public CuentaCallback(LoginMesa main) {
        this.main = main;
    }
    @Override
    public void onResponse(Call <List<Cuenta>> call, Response<List<Cuenta>> response) {

        Cuenta c = response.body().get(0);

        main.obtenerCuenta(c);
    }

    @Override
    public void onFailure(Call<List<Cuenta>> call, Throwable t) {

        Toast.makeText(main,t.toString(),Toast.LENGTH_SHORT).show();
    }
}
