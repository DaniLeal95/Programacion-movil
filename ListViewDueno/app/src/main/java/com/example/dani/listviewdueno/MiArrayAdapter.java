package com.example.dani.listviewdueno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by Dani on 06/12/2016.
 */

public class MiArrayAdapter  extends ArrayAdapter<Object> {


    public MiArrayAdapter(Context context, int rowView, Vector<Object> objects) {
        super(context, rowView,objects);
    }
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MiViewHolder d = null;
        MiViewHolderdos dd = null;

        if (row==null){

            if (getItemViewType(position) ==0) {


                row = inflater.inflate(R.layout.filaperro, parent, false);
                TextView nombreperro = (TextView) row.findViewById(R.id.nombreperro);
                ImageView imagenperro = (ImageView) row.findViewById(R.id.fotoperro);
                TextView razaperro = (TextView) row.findViewById(R.id.razaperro);

                dd = new MiViewHolderdos(nombreperro, imagenperro, razaperro);

                row.setTag(dd);

            }else{
                row = inflater.inflate( R.layout.filapersona,parent,false);
                TextView nombredueño =(TextView) row.findViewById(R.id.txtnombredueno);
                TextView tlfdueño = (TextView) row.findViewById(R.id.txttlfdueno);
                TextView direcciondueño = (TextView) row.findViewById(R.id.txtdireccion);


                d = new MiViewHolder(nombredueño,tlfdueño,direcciondueño);
                row.setTag(d);

            }

        }
        else{
            if(getItemViewType(position)==0) {
                dd = (MiViewHolderdos) row.getTag();
            }
            else{
                d=(MiViewHolder) row.getTag();
            }


        }

        if (getItemViewType(position)==0) {
            Perro perro = (Perro) getItem(position);
            TextView nombreperro=  dd.getNombreperro();
            ImageView imagenperro =  dd.getImgperro();
            TextView razaperro = dd.getRazaperro();

            nombreperro.setText(perro.getNombre());
            imagenperro.setImageResource(perro.getImg());
            razaperro.setText(perro.getRaza());

        }else {
            Dueño dueño = (Dueño) getItem(position);
            TextView nombredueño= d.getTxtnombredueño();
            TextView tlfdueño=  d.getTxttlfdueño();
            TextView direcciondueño=  d.getTxtDireccion();
            nombredueño .setText(dueño.getNombre());
            tlfdueño.setText(dueño.getTlf());
            direcciondueño.setText(dueño.getDireccion());
        }



        return row;
    }




    @Override
    public int getItemViewType(int position){
        int fila;

        if(getItem(position) instanceof Perro){
            fila=0;

        }else{
            fila=1;
        }


        return fila;
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }
}
