package com.iesnervion.dleal.layoutinflaterandviewholder;

/**
 * Created by dleal on 2/11/16.
 */

public class Jugador {
    //Atributos

    private String nombre;
    private String apellidos;
    private int cara;
    private String nacionalidad;

    //Constructores

    public Jugador(){
        this.nombre="";
        this.apellidos="";
        this.cara=0;
        this.nacionalidad="";
    }
    public Jugador (String nombre,String apellidos,int cara,String nacionalidad){
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.cara=cara;
        this.nacionalidad=nacionalidad;
    }

    //Consultores

    public String getNombre(){return this.nombre;}
    public String getApellidos(){return this.apellidos;}
    public int getCara(){return this.cara;}
    public String getNacionalidad(){return this.nacionalidad;}

    //Modificadores

    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setApellidos(String apellidos){
        this.apellidos=apellidos;
    }
    public void setCara(int cara)  {this.cara=cara;}
    public void setNacionalidad(String nacionalidad){
        this.nacionalidad=nacionalidad;
    }
}
