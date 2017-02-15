package com.iesnervion.dleal.appfebrerobar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dani on 30/01/2017.
 */

public class Cuenta{

    @SerializedName("idcuenta")
    @Expose
    private int idcuenta;
    @SerializedName("nummesa")
    @Expose
    private int nummesa;
    @SerializedName("finalizada")
    @Expose
    private int finalizada;
    @SerializedName("preciofinal")
    @Expose
    private double preciofinal;
    @SerializedName("listdetallecuenta")
    @Expose
    private List<DetallesCuenta> listdetallecuenta;
    @SerializedName("fecha")
    @Expose
    private String fecha;


    //Constructor
    public Cuenta(int idcuenta, int nummesa,List<DetallesCuenta> listdetallecuenta,String fecha,double preciofinal,int finalizada) {
        this.idcuenta = idcuenta;
        this.nummesa = nummesa;
        this.preciofinal = preciofinal;

        if(listdetallecuenta!=null)
            this.listdetallecuenta = listdetallecuenta;
        else this.listdetallecuenta = new ArrayList<>();

        this.fecha=fecha;
        this.finalizada=finalizada;

    }
    //TODO: Quitar este constructor cuando coga los datos de la api.
    //Constructor
    public Cuenta(int idcuenta){
        this.idcuenta = idcuenta;
        this.preciofinal = 0.0;
        listdetallecuenta = new ArrayList<>();
    }


    public Cuenta() {
    }

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    public int getNummesa() {
        return nummesa;
    }

    public void setNummesa(int nummesa) {
        this.nummesa = nummesa;
    }

    public double getPreciofinal() {
        return this.preciofinal;
    }

    public void setPreciofinal(double preciofinal) {
        this.preciofinal = preciofinal;
    }

    public List<DetallesCuenta> getDetallesCuentas() {
        return listdetallecuenta;
    }

    public void setDetallesCuentas(List<DetallesCuenta> detallesCuentas) {
        this.listdetallecuenta = detallesCuentas;
    }

    public int getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(int finalizada) {
        this.finalizada = finalizada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


}
