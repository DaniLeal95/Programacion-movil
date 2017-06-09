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
 * Created by Dani on 16/02/2017.
 */

public class ObtenerCuentaFragmentCallback  implements Callback<List<Cuenta>> {
    private CuentaFragment main;

    public ObtenerCuentaFragmentCallback(CuentaFragment main) {
        this.main = main;
    }

    @Override
    public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {

        Utilidades u = new Utilidades(main.getContext());
        if(response.body()!=null){

            Cuenta c = response.body().get(0);
            if(c.getDetallesCuentas()==null){
                c.setDetallesCuentas(new ArrayList<DetallesCuenta>());
            }

            if(u.getCuenta()==null ) {

                u.borrarCuenta();
                u.insertCuenta(c);

                main.esperaCuenta(c);
            }
            //Si la cuenta en la que estaba antes es la misma
            else if(u.getCuenta().getIdcuenta()==c.getIdcuenta()){
                    u.borrarCuenta();
                    u.insertCuenta(c);

                    main.esperaCuenta(c);

            }
            //Si ha expirado porque ya ha sido pagada, por ejemplo y hay una cuenta activa con esa mesa
            else{
                u.borrarCuenta();
                main.cuentaExpirada();
                //TODO Pensar solucion.
            }

        }
        //Si ha expirado porque ya ha sido pagada, por ejemplo y no hay cuentas activas con esa mesa
        else{
            u.borrarCuenta();
            main.cuentaExpirada();
        }



    }

    @Override
    public void onFailure(Call<List<Cuenta>> call, Throwable t) {
        Utilidades u = new Utilidades(main.getContext());
        u.borrarCuenta();
        main.cuentaExpirada();
    }
}
