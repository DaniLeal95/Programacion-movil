package com.iesnervion.dleal.layoutinflaterandviewholder;

/**
 * Created by dleal on 2/11/16.
 */

public class Estadio {
    //Propiedades
    private String ciudad;
    private int capacidad;
    private int imagen;
    private String nombre;



    //Constructores


    public Estadio(String ciudad,int capacidad,int imagen,String nombre){
        this.ciudad = ciudad;
        this.capacidad = capacidad;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    //consultores y modificadores
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
