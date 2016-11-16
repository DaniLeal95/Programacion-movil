package com.iesnervion.dleal.appsanteriores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by dleal on 15/11/16.
 */

public class MiArrayAdapter extends ArrayAdapter {

    public MiArrayAdapter(Context context, int rowView, Vector<App> objetos) {
        super(context, rowView,  objetos);
    }

    public View getView(
        int position, View convertView,
                ViewGroup parent) {

        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (row == null) {

            row = inflater.inflate(R.layout.cell, parent, false);
            ViewHolder viewHolder=new ViewHolder(row);

            App aplicacion = (App) getItem(position);

            TextView nombre = viewHolder.getTitulo();
            String titulo= aplicacion.getTitulo();

            ImageView imagen = viewHolder.getImagen();
            int img= aplicacion.getImage();

            nombre.setText(""+titulo);
            imagen.setImageResource(img);
        }
        return row;
    }

    @Override
    public int getItemViewType(int position){
        int fila=0;


        return (fila);
    }
    @Override
    public int getViewTypeCount(){
        return 1;
    }

}
