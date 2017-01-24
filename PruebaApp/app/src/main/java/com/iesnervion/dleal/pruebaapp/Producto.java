package com.iesnervion.dleal.pruebaapp;

/**
 * Created by dleal on 10/01/17.
 */

public class Producto {

    //Propiedades
    private int id,cantidad;
    private String nombre,tipo;


    public Producto(int id, String tipo,int cantidad, String nombre) {
        this.id = id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.tipo = tipo;


    }

    public Producto() {

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
