package com.iesnervion.dleal.appfebrerobar.model;

import java.util.List;

/**
 * Created by Dani on 30/01/2017.
 */

public class Cuenta {

    private int idcuenta,nummesa;
    private double preciofinal;
    private List<Producto> productos;

    public Cuenta(int idcuenta, int nummesa, double preciofinal, List<Producto> productos) {
        this.idcuenta = idcuenta;
        this.nummesa = nummesa;
        this.preciofinal = preciofinal;
        this.productos = productos;
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
        return preciofinal;
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
