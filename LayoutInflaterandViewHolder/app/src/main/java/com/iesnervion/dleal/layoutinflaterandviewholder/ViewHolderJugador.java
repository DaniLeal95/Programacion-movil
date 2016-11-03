package com.iesnervion.dleal.layoutinflaterandviewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dleal on 2/11/16.
 */

public class ViewHolderJugador {

    ImageView cara;
    TextView nombreapellidos;

    TextView nacionalidad;


    public ViewHolderJugador (View view){
        cara = (ImageView) view.findViewById(R.id.cara);
        nombreapellidos= (TextView) view.findViewById(R.id.nombrejugador);

        nacionalidad= (TextView) view.findViewById(R.id.ciudadjugador);

    }

    public ImageView getCara(){return this.cara;}
    public TextView getNombreapellidos(){return this.nombreapellidos;}

    public TextView getNacionalidad(){return this.nacionalidad;}
}
