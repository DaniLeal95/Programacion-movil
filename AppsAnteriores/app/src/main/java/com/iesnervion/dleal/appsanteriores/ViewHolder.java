package com.iesnervion.dleal.appsanteriores;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * Created by dleal on 15/11/16.
 */

public class ViewHolder {

    private TextView titulo;
    private ImageView imagen;

    public ViewHolder (View v){
        titulo=(TextView) v.findViewById(R.id.titulo);
        imagen= (ImageView) v.findViewById(R.id.imagen);
    }

    public TextView getTitulo(){
        return this.titulo;
    }
    public ImageView getImagen(){
        return this.imagen;
    }
}
