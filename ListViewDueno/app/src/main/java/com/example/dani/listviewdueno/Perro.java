package com.example.dani.listviewdueno;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dani on 06/12/2016.
 */

public class Perro implements Parcelable{

    private String nombre;
    private int img;
    private String raza;
    private int iddueño;

    public Perro(String nombre,int img,String raza,int iddueño){
        this.img=img;
        this.nombre=nombre;
        this.raza=raza;
        this.iddueño=iddueño;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getIddueño() {
        return iddueño;
    }

    public void setIddueño(int iddueño) {
        this.iddueño = iddueño;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeValue(this.iddueño);
        parcel.writeString(this.nombre);
        parcel.writeString(this.raza);
        parcel.writeValue(this.img);
    }

    protected Perro(Parcel in) {
        this.iddueño = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nombre = in.readString();
        this.raza = in.readString();
        this.img =  (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Perro> CREATOR = new Creator<Perro>() {
        @Override
        public Perro createFromParcel(Parcel source) {
            return new Perro(source);
        }

        @Override
        public Perro[] newArray(int size) {
            return new Perro[size];
        }
    };
}
