package com.iesnervion.dani.boletin5ejercicio1;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import java.util.Vector;

import static android.graphics.Paint.Align.LEFT;
import static com.iesnervion.dani.boletin5ejercicio1.R.layout.fila_layout;

/**
 * Created by dleal on 19/10/16.
 */

public class MiArrayAdapter extends ArrayAdapter {

    public MiArrayAdapter(Context context,int rowView,int nombre, Vector<Equipo> equipos){
        super(context,rowView,nombre,equipos);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view = super.getView(position, convertView, parent);
        Equipo equipo=(Equipo) getItem(position);



        // RelativeLayout rl=(RelativeLayout) R.layout.fila_layout;
         ImageView logo = (ImageView) view.findViewById(R.id.logo);
         TextView nombre = (TextView) view.findViewById(R.id.nombre);


        /*if(position%2==0) {
            LayoutParams layoutParams = (LayoutParams) logo2.getLayoutParams();
            logo.setLayoutParams(layoutParams);
            LayoutParams layoutParams2 = (LayoutParams) nombre2.getLayoutParams();
            nombre.setLayoutParams(layoutParams2);

        }*/
            nombre.setText(equipo.getNombre());
            logo.setImageResource(equipo.getLogo());

        return view;
    }


}
