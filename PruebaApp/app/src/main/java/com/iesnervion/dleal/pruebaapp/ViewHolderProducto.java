package com.iesnervion.dleal.pruebaapp;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dleal on 10/01/17.
 */

public class ViewHolderProducto {

    private ImageView imagenProducto;
    private TextView tituloProducto;

    public ViewHolderProducto(ImageView imagenProducto, TextView tituloProducto) {
        this.imagenProducto = imagenProducto;
        this.tituloProducto = tituloProducto;
    }

    public ImageView getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(ImageView imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public TextView getTituloProducto() {
        return tituloProducto;
    }

    public void setTituloProducto(TextView tituloProducto) {
        this.tituloProducto = tituloProducto;
    }
}
