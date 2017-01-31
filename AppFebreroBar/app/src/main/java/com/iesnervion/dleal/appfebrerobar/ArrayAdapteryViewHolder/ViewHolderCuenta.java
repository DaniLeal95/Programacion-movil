package com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder;

import com.iesnervion.dleal.appfebrerobar.customfont.Customfont;

/**
 * Created by dleal on 31/01/17.
 */

public class ViewHolderCuenta {
    private Customfont uds,descripcion,pvp,importe;

    public ViewHolderCuenta(Customfont uds, Customfont descripcion, Customfont pvp, Customfont importe) {
        this.uds = uds;
        this.descripcion = descripcion;
        this.pvp = pvp;
        this.importe = importe;
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
