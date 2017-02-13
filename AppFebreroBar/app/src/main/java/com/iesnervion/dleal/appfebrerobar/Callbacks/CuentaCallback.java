package com.iesnervion.dleal.appfebrerobar.Callbacks;

import com.iesnervion.dleal.appfebrerobar.LoginMesa;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Dani on 12/02/2017.
 */

public class CuentaCallback implements Callback<Cuenta> {

    private LoginMesa main;

    public CuentaCallback(LoginMesa main) {
        this.main = main;
    }
    @Override
    public void onResponse(Call<Cuenta> call, Response<Cuenta> response) {

    }

    @Override
    public void onFailure(Call<Cuenta> call, Throwable t) {

    }
}
