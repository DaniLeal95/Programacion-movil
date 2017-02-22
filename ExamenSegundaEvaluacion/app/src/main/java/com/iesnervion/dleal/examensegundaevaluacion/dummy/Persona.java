package com.iesnervion.dleal.examensegundaevaluacion.dummy;

/**
 * Created by dleal on 22/02/17.
 */

public class Persona {


    public int getId() {
        return id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    private int id,edad;
    private String telefono,nombre;
    private char sexo;


    public Persona(int id, String nombre, int edad, String telefono, char sexo) {
        this.id = id;
        this.edad = edad;
        this.telefono = telefono;
        this.sexo = sexo;
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
