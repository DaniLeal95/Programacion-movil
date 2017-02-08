package com.iesnervion.dleal.appfebrerobar.model;

/**
 * Created by Dani on 08/02/2017.
 */

public class DetallesCuenta {

    private Producto producto;
    private int cantidad;

    public DetallesCuenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
