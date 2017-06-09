package com.iesnervion.dleal.appfebrerobar.Callbacks;

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
 * Created by danie on 08/06/2017.
 */

public class ObtenerCuentaPrincipalCallback implements Callback<List<Cuenta>> {
    private Principal main;

    public ObtenerCuentaPrincipalCallback(Principal main) {
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

                main.cuentaActiva();

            }
            //Si la cuenta más reciente de esa mesa está finalizada, debemos generar otra
            else{
               main.cuentaFinalizada();
            }
        }


    }

    @Override
    public void onFailure(Call<List<Cuenta>> call, Throwable t) {
        //main.PostCuenta();
    }
}
