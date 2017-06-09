package com.iesnervion.dleal.appfebrerobar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by danie on 28/05/2017.
 */

public class Categoria implements Parcelable {

    @SerializedName("idcategoria")
    @Expose
    private Integer idcategoria;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("operativo")
    @Expose
    private Integer operativo;

    public Categoria(){}
    public Categoria(int idcategoria,String nombre,int operativo){
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.operativo = operativo;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }


    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Integer getOperativo() {
        return operativo;
    }


    public void setOperativo(Integer operativo) {
        this.operativo = operativo;
    }



    @Override
    public String toString() {
        return "Producto{" +

                "idcategoria=" + idcategoria +
                ", nombre='" + nombre + '\'' +
                ", operativo=" + operativo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idcategoria);
        dest.writeString(nombre);
        dest.writeInt(operativo);
    }

    protected Categoria(Parcel in) {
        idcategoria = in.readInt();
        nombre = in.readString();
        operativo = in.readInt();
    }

    public static final Parcelable.Creator<Categoria> CREATOR = new Parcelable.Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel in) {
            return new Categoria(in);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };
}
