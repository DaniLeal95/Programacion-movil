package com.iesnervion.dani.boletin5ejercicio1;

/**
 * Created by dleal on 19/10/16.
 */

public class Equipo {

    private String nombre;
    private int logo;

    public Equipo(String nombre,int logo){

        this.nombre=nombre;
        this.logo=logo;

    }

    public String getNombre(){
        return this.nombre;
    }
    public int getLogo(){
        return this.logo;
    }

}
