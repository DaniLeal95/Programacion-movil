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

        List<Producto> productos = new ArrayList<>();

        productos.add(new Producto(1,1,"Cerveza",1.20));
        productos.add(new Producto(1,1,"Cerveza",1.20));
        productos.add(new Producto(1,1,"Cerveza",1.20));
        productos.add(new Producto(2,1,"CocaCola",1.50));
        productos.add(new Producto(2,1,"CocaCola",1.50));
        productos.add(new Producto(3,2,"Ensaladilla",3.00));
        productos.add(new Producto(1,1,"Cerveza",1.20));
        productos.add(new Producto(4,3,"Alitas de pollo",2.50));

        this.cuenta.setProductos(productos);

        Mesa m = new Mesa(1,"11111");
        Mesa m2 = new Mesa(2,"22222");
        this.addMesa(m);
        this.addMesa(m2);
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
