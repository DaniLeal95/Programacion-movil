package com.iesnervion.dleal.appfebrerobar.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dani on 30/01/2017.
 */

public class Cuenta {

    private int idcuenta,nummesa;
    private double preciofinal;
    private List<DetallesCuenta> detallesCuentas;

    //Constructor
    public Cuenta(int idcuenta, int nummesa,List<DetallesCuenta> detallesCuentas) {
        this.idcuenta = idcuenta;
        this.nummesa = nummesa;
        this.preciofinal = 0.0;
        this.detallesCuentas = detallesCuentas;
    }
    //TODO: Quitar este constructor cuando coga los datos de la api.
    //Constructor
    public Cuenta(int idcuenta){
        this.idcuenta = idcuenta;
        this.preciofinal = 0.0;
        detallesCuentas = new ArrayList<>();
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
        double precio=0.0;

        for(int i=0;i<this.getDetallesCuentas().size();i++){
            precio+=(this.detallesCuentas.get(i).getProducto().getPrecio()*this.detallesCuentas.get(i).getCantidad());
        }
        return precio;
    }

    public void setPreciofinal(double preciofinal) {
        this.preciofinal = preciofinal;
    }

    public List<DetallesCuenta> getDetallesCuentas() {
        return detallesCuentas;
    }

    public void setDetallesCuentas(List<DetallesCuenta> detallesCuentas) {
        this.detallesCuentas = detallesCuentas;
    }
}
