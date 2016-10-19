package com.iesnervion.dani.boletin5ejercicio1;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by dleal on 19/10/16.
 */

public class MiArrayAdapter extends ArrayAdapter {
    private Vector<Equipo> equipos;

    public MiArrayAdapter(Context context, int rowView, Vector<Equipo> equipos){
        super(context, rowView,equipos);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = super.getView(position,convertView,parent);

        ImageView logo = (ImageView) view.findViewById(R.id.logo);
        TextView nombre= (TextView) view.findViewById(R.id.nombre);

        nombre.setText(equipos.get(position).getNombre());

        return view;
    }

}
