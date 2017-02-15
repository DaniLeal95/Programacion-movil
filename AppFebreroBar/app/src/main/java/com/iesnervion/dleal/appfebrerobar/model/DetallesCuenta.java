package com.iesnervion.dleal.appfebrerobar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dani on 08/02/2017.
 */

public class DetallesCuenta implements Parcelable {
    @SerializedName("producto")
    @Expose
    private Producto producto;
    @SerializedName("cantidad")
    @Expose
    private int cantidad;

    public DetallesCuenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.producto,flags);
        dest.writeInt(this.cantidad);
    }

    protected DetallesCuenta(Parcel in) {
        this.producto = in.readParcelable(Producto.class.getClassLoader());
        this.cantidad = in.readInt();
    }

    public static final Creator<DetallesCuenta> CREATOR = new Creator<DetallesCuenta>() {
        @Override
        public DetallesCuenta createFromParcel(Parcel source) {
            return new DetallesCuenta(source);
        }

        @Override
        public DetallesCuenta[] newArray(int size) {
            return new DetallesCuenta[size];
        }
    };
}
