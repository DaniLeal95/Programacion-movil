package com.iesnervion.dleal.appsanteriores;

import android.view.View;
import android.widget.TextView;



/**
 * Created by dleal on 15/11/16.
 */

public class ViewHolder {

    private TextView titulo;

    public ViewHolder (View v){
        titulo=(TextView) v.findViewById(R.id.titulo);
    }

    public TextView getTitulo(){
        return this.titulo;
    }
}
