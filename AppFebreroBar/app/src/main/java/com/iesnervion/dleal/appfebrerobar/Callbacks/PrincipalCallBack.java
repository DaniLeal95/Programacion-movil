package com.iesnervion.dleal.appfebrerobar.Callbacks;

import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.Fragments.CuentaFragment;
import com.iesnervion.dleal.appfebrerobar.LoginMesa;
import com.iesnervion.dleal.appfebrerobar.NuevaComanda;
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
 * Created by dleal on 14/02/17.
 */

public class PrincipalCallBack implements Callback<List<Cuenta>> {

    private NuevaComanda main;

    public PrincipalCallBack(NuevaComanda main) {
        this.main = main;
    }
    @Override
    public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {

        Cuenta c = null;
        if(response.body()!=null){

            c = response.body().get(0);
            if(c.getDetallesCuentas()==null){
                c.setDetallesCuentas(new ArrayList<DetallesCuenta>());
            }


            Utilidades u = new Utilidades(main);
            u.borrarCuenta();
            u.insertCuenta(c);
        }


    }

    @Override
    public void onFailure(Call<List<Cuenta>> call, Throwable t) {

        Toast.makeText(main,t.toString(),Toast.LENGTH_SHORT).show();
    }
}
