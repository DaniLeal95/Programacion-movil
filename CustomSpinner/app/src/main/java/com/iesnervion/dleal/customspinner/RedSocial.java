package com.iesnervion.dleal.customspinner;

/**
 * Created by dleal on 9/11/16.
 */

public class RedSocial {
    //Atributos
    private String nombre;
    private int imagen;

    //Constructores

    //Por defecto
    public RedSocial(){
        nombre="Default";
        imagen= android.R.mipmap.sym_def_app_icon;
    }

    //Con atributos
    public RedSocial(String nombre,int imagen){
        this.nombre=nombre;
        this.imagen=imagen;
    }

    //Getters&Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
