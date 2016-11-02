package com.iesnervion.dleal.layoutinflaterandviewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dleal on 2/11/16.
 */

public class ViewHolderEquipo {
    ImageView logo;
    TextView nombre;
    TextView inicial;


    public ViewHolderEquipo (View view){
        logo = (ImageView) view.findViewById(R.id.icon);
        nombre = (TextView) view.findViewById(R.id.label);
        inicial = (TextView) view.findViewById(R.id.labelinicial);
    }

    public ImageView getLogo(){return this.logo;}
    public TextView getNombre(){return this.nombre;}
    public TextView getInicial(){return this.inicial;}
}
