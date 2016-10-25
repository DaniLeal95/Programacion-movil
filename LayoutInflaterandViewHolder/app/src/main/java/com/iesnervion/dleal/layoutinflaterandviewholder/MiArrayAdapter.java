package com.iesnervion.dleal.layoutinflaterandviewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Vector;



/**
 * Created by dleal on 19/10/16.
 */

public class MiArrayAdapter extends ArrayAdapter {

    public MiArrayAdapter(Context context,int rowView,int nombre, Vector<Equipo> equipos){
        super(context,rowView,nombre,equipos);
    }


    public View getView(int position, View convertView,
                        ViewGroup parent) {

        View row = convertView;

        if (row==null){
            LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.row, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder(row);


        ImageView logo = viewHolder.getLogo();
        TextView nombre = viewHolder.getNombre();


        Equipo equipo= (Equipo) getItem(position);

        logo.setImageResource(equipo.getLogo());
        nombre.setText(equipo.getNombre());

        return(row);
    }

    public class ViewHolder{
        ImageView logo;
        TextView nombre;

        public ViewHolder (View view){
            logo = (ImageView) view.findViewById(R.id.icon);
            nombre = (TextView) view.findViewById(R.id.label);
        }

        public ImageView getLogo(){return this.logo;}
        public TextView getNombre(){return this.nombre;}
    }
}













