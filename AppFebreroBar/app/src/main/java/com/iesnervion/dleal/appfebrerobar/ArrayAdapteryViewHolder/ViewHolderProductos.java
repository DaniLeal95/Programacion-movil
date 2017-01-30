package com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder;

import android.widget.TextView;

/**
 * Created by dleal on 30/01/17.
 */

public class ViewHolderProductos {

    private TextView nombre;
    private TextView precio;

    public ViewHolderProductos(TextView nombre, TextView precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getPrecio() {
        return precio;
    }
}
