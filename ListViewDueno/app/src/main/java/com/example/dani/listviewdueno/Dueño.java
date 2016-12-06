package com.example.dani.listviewdueno;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dani on 06/12/2016.
 */

public class Dueño implements Parcelable{
    private String nombre;
    private String tlf;
    private int id;
    private String direccion;

    public Dueño(String nombre, String tlf, int id, String direccion) {
        this.nombre = nombre;
        this.tlf = tlf;
        this.id = id;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeValue(this.id);
        parcel.writeString(this.nombre);
        parcel.writeString(this.tlf);
        parcel.writeString(this.direccion);
    }

    protected Dueño(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nombre = in.readString();
        this.tlf = in.readString();
        this.direccion =  in.readString();
    }

    public static final Creator<Dueño> CREATOR = new Creator<Dueño>() {
        @Override
        public Dueño createFromParcel(Parcel source) {
            return new Dueño(source);
        }

        @Override
        public Dueño[] newArray(int size) {
            return new Dueño[size];
        }
    };
}
