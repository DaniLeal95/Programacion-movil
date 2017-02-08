package com.iesnervion.dleal.appfebrerobar.model;

/**
 * Created by Dani on 29/01/2017.
 */

public class Producto {

    private int idproducto,idcategoria;
    private String nombre;
    private double precio;

    public Producto() {
    }

    public Producto(int idproducto, int idcategoria, String nombre, double precio) {
        this.idproducto = idproducto;
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idproducto=" + idproducto +
                ", idcategoria=" + idcategoria +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
