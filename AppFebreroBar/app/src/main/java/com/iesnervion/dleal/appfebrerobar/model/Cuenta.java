package com.iesnervion.dleal.appfebrerobar.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dani on 30/01/2017.
 */

public class Cuenta {

    private int idcuenta,nummesa;
    private double preciofinal;
    private List<Producto> productos;

    //Constructor
    public Cuenta(int idcuenta, int nummesa,  List<Producto> productos) {
        this.idcuenta = idcuenta;
        this.nummesa = nummesa;
        this.preciofinal = 0.0;
        this.productos = productos;
    }
    //TODO: Quitar este constructor cuando coga los datos de la api.
    //Constructor
    public Cuenta(int idcuenta){
        this.idcuenta = idcuenta;
        this.preciofinal = 0.0;
        productos = new ArrayList<>();
    }


    public Cuenta() {
    }

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    public int getNummesa() {
        return nummesa;
    }

    public void setNummesa(int nummesa) {
        this.nummesa = nummesa;
    }

    public double getPreciofinal() {
        double precio=0.0;

        for(int i=0;i<this.getProductos().size();i++){
            precio+=this.getProductos().get(i).getPrecio();
        }
        return precio;
    }

    public void setPreciofinal(double preciofinal) {
        this.preciofinal = preciofinal;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
