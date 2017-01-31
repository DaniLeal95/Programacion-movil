package com.iesnervion.dleal.appfebrerobar.datos;

import android.app.Application;

import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.Mesa;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dani on 31/01/2017.
 */

public class Listados extends Application {

    private List<Mesa> mesas;
    private Cuenta cuenta;

    public Listados() {

        mesas = new ArrayList<>();
        cuenta = new Cuenta(1);
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public void addMesa(Mesa mesa){
        this.mesas.add(mesa);
    }

    public void addProducttoCuenta(Producto producto){
        this.cuenta.getProductos().add(producto);
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
