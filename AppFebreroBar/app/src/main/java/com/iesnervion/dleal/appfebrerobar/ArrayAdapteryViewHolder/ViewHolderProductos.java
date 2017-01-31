package com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder;

import android.widget.TextView;

/**
 * Created by dleal on 30/01/17.
 */

public class ViewHolderProductos {

    private com.iesnervion.dleal.appfebrerobar.customfont.Customfont nombre;
    private com.iesnervion.dleal.appfebrerobar.customfont.Customfont precio;

    public ViewHolderProductos(com.iesnervion.dleal.appfebrerobar.customfont.Customfont nombre, com.iesnervion.dleal.appfebrerobar.customfont.Customfont precio) {
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
