package com.iesnervion.dleal.layoutinflaterandviewholder;

/**
 * Created by dleal on 19/10/16.
 */

public class Equipo {

    private String nombre;
    private int logo;

    public Equipo(){
        this.nombre="";
        this.logo=0;
    }
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

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
