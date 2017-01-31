package com.iesnervion.dleal.appfebrerobar.model;

/**
 * Created by Dani on 31/01/2017.
 */

public class Mesa {
    private int nummesa;
    private String codigo;

    public Mesa(int nummesa, String codigo) {
        this.nummesa = nummesa;
        this.codigo = codigo;
    }

    public int getNummesa() {
        return nummesa;
    }

    public void setNummesa(int nummesa) {
        this.nummesa = nummesa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
