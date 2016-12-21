package com.iesnervion.dleal.nbaficheros.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.iesnervion.dleal.nbaficheros.MainActivity;
import com.iesnervion.dleal.nbaficheros.Utilidades.Utilidades;

import java.io.Serializable;

/**
 * Created by dleal on 7/12/16.
 */

public class Jugador implements Serializable {

    //SERIALIZABLE
    private static final long serialVersionUID = -2995685764709883913L;

    //Propiedad estatica para conocer la iddelutimo Jugador creado
    public static long contadojugadores=0;



    private String nombre,posicion;
    private int img,altura,peso;
    private long id;


    public Jugador(String nombre, String posicion, int img, int altura, int peso,Context context) {
        /*Estas 4 lineas son para obtener el idCliente*/
        Utilidades u=new Utilidades(context);
        if(contadojugadores!=0){id=contadojugadores+1;}
        else{id=u.cogerUltimaId("idjugadores.dat")+1;}
        contadojugadores=id;
        u.escribirUltimaId(id,"idjugadores.dat");

		/*-------------------------------------------*/
        this.nombre = nombre;
        this.posicion = posicion;
        this.img = img;
        this.altura = altura;
        this.peso = peso;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public long getId() {
        return id;
    }


}
