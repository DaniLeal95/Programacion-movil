package com.iesnervion.dleal.appfebrerobar.Callbacks;

import com.iesnervion.dleal.appfebrerobar.Fragments.CuentaFragment;
import com.iesnervion.dleal.appfebrerobar.Principal;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dani on 15/02/2017.
 */

public class ObtenerCuentaCallback implements Callback<List<Cuenta>>{
    private Principal main;

    public ObtenerCuentaCallback(Principal main) {
        this.main = main;
    }

    @Override
    public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {

        Cuenta c = (Cuenta) response.body().get(0);

        main.rellenaLista(c);
       // main.getActivity().rellenaLista
    }

    @Override
    public void onFailure(Call<List<Cuenta>> call, Throwable t) {

    }
}
