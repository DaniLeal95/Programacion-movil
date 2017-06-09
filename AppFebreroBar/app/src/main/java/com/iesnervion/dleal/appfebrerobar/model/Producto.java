package com.iesnervion.dleal.appfebrerobar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dani on 29/01/2017.
 */

public class Producto implements Parcelable {

    @SerializedName("idproducto")
    @Expose
    private Integer idproducto;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("precio")
    @Expose
    private Double precio;
    @SerializedName("idcategoria")
    @Expose
    private Integer idcategoria;
    @SerializedName("operativo")
    @Expose
    private Integer operativo;

    public Producto() {
    }

    public Producto(int idproducto, String nombre, double precio, int idcategoria,int operativo) {
        this.idproducto = idproducto;
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.precio = precio;
        this.operativo = operativo;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getOperativo() {
        return operativo;
    }

    public void setOperativo(int operativo) {
        this.operativo = operativo;
    }



    @Override
    public String toString() {
        return "Producto{" +
                "idproducto=" + idproducto +
                ", idcategoria=" + idcategoria +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio + '\'' +
                ", operativo=" + operativo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idproducto);
        dest.writeString(nombre);
        dest.writeDouble(precio);
        dest.writeInt(idcategoria);
        dest.writeInt(operativo);
    }

    protected Producto(Parcel in) {
        idproducto = in.readInt();
        nombre = in.readString();
        precio = in.readDouble();
        idcategoria = in.readInt();
        operativo = in.readInt();
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };
}
