package com.iesnervion.dleal.layoutinflaterandviewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dleal on 2/11/16.
 */

public class ViewHolderEstadio {
    private ImageView imagenEstadio;
    private TextView nombre;
    private TextView ciudad;
    private TextView capacidad;

    public ViewHolderEstadio(View v) {
        this.imagenEstadio = (ImageView) v.findViewById(R.id.imagenestadio);
        this.nombre = (TextView) v.findViewById(R.id.nameStadium);
        this.ciudad = (TextView) v.findViewById(R.id.estadiociudad);
        this.capacidad = (TextView) v.findViewById(R.id.estadiocapacidad);
    }

    public ImageView getImagenEstadio() {
        return imagenEstadio;
    }

    public TextView getNombre() {
        return nombre;
    }

    public TextView getCiudad() {
        return ciudad;
    }

    public TextView getCapacidad() {
        return capacidad;
    }
}
