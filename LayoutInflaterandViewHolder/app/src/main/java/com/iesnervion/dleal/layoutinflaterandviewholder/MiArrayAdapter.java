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
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (row==null){


            if(this.getItemViewType(position)==0) {

                row = inflater.inflate(R.layout.row, parent, false);
            }
            else{

                row = inflater.inflate(R.layout.row2, parent, false);
            }
        }

        ViewHolder viewHolder = new ViewHolder(row);


        ImageView logo = viewHolder.getLogo();
        TextView nombre = viewHolder.getNombre();
        TextView inicial = viewHolder.getInicial();


        Equipo equipo= (Equipo) getItem(position);
        logo.setImageResource(equipo.getLogo());
        nombre.setText(equipo.getNombre());

        inicial.setText(""+equipo.getNombre().charAt(0));



        return(row);
    }

    @Override
    public int getItemViewType(int position){
        return (position%2);
    }
    @Override
    public int getViewTypeCount(){
        return 2;
    }

    public class ViewHolder{
        ImageView logo;
        TextView nombre;
        TextView inicial;

        public ViewHolder (View view){
            logo = (ImageView) view.findViewById(R.id.icon);
            nombre = (TextView) view.findViewById(R.id.label);
            inicial = (TextView) view.findViewById(R.id.labelinicial);
        }

        public ImageView getLogo(){return this.logo;}
        public TextView getNombre(){return this.nombre;}
        public TextView getInicial(){return this.inicial;}

    }
}













