package com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder;

import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.iesnervion.dleal.appfebrerobar.customfont.Customfont;

/**
 * Created by dleal on 31/01/17.
 */

public class ViewHolderCuenta {
    private Customfont uds,descripcion,pvp,importe;
    private ImageButton sumacantidad,restacantidad;

    public ImageButton getSumacantidad() {
        return sumacantidad;
    }

    public ImageButton getRestacantidad() {
        return restacantidad;
    }

    public ViewHolderCuenta(Customfont uds, Customfont descripcion, Customfont pvp, Customfont importe,ImageButton sumacantidad,ImageButton restacantidad) {
        this.uds = uds;
        this.descripcion = descripcion;
        this.pvp = pvp;
        this.importe = importe;
        this.sumacantidad=sumacantidad;
        this.restacantidad=restacantidad;
    }

    public Customfont getUds() {
        return uds;
    }

    public Customfont getDescripcion() {
        return descripcion;
    }

    public Customfont getPvp() {
        return pvp;
    }

    public Customfont getImporte() {
        return importe;
    }



}
