package com.iesnervion.dleal.appfebrerobar.Callbacks;

import com.iesnervion.dleal.appfebrerobar.Fragments.CuentaFragment;
import com.iesnervion.dleal.appfebrerobar.LoginMesa;
import com.iesnervion.dleal.appfebrerobar.Principal;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dani on 15/02/2017.
 */

public class ObtenerCuentaLoginCallback implements Callback<List<Cuenta>>{
    private LoginMesa main;

    public ObtenerCuentaLoginCallback(LoginMesa main) {
        this.main = main;
    }

    @Override
    public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {


        if(response.body()!=null){

            Cuenta c = response.body().get(0);

            if(c.getFinalizada()==0) {
                if (c.getDetallesCuentas() == null) {
                    c.setDetallesCuentas(new ArrayList<DetallesCuenta>());
                }


                Utilidades u = new Utilidades(main);
                u.borrarCuenta();
                u.insertCuenta(c);

                main.esperaCuenta();

            }
            //Si la cuenta más reciente de esa mesa está finalizada, debemos generar otra
            else{
                main.PostCuenta();
            }
        }
        else{
            main.PostCuenta();
        }


    }

    @Override
    public void onFailure(Call<List<Cuenta>> call, Throwable t) {
        main.PostCuenta();
    }
}
