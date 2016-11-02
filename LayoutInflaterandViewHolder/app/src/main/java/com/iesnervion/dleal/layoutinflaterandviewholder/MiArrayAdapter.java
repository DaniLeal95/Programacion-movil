package com.iesnervion.dleal.layoutinflaterandviewholder;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.Vector;



/**
 * Created by dleal on 19/10/16.
 */

public class MiArrayAdapter extends ArrayAdapter {

    public MiArrayAdapter(Context context,int rowView,int nombre, Vector<Object> objetos){
        super(context,rowView,nombre,objetos);
    }


    public View getView(int position, View convertView,
                        ViewGroup parent) {

        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (row==null){


            switch (this.getItemViewType(position)) {
                case 0:
                    ViewHolderEquipo viewHolderequipo;
                    row = inflater.inflate(R.layout.rowequipo, parent, false);
                    viewHolderequipo = new ViewHolderEquipo(row);


                    ImageView logo = viewHolderequipo.getLogo();
                    TextView nombre = viewHolderequipo.getNombre();



                    Equipo equipo= (Equipo) getItem(position);
                    logo.setImageResource(equipo.getLogo());
                    nombre.setText(equipo.getNombre());
                    break;

                case 1:

                    row = inflater.inflate(R.layout.rowjugador, parent, false);
                    ViewHolderJugador viewHolderJugador = new ViewHolderJugador(row);
                    ImageView cara = viewHolderJugador.getCara();
                    TextView nombreapellidos= viewHolderJugador.getNombreapellidos();
                    TextView nacionalidad=viewHolderJugador.getNacionalidad();

                    Jugador jugador = (Jugador) getItem(position);
                    cara.setImageResource(jugador.getCara());
                    nombreapellidos.setText(jugador.getNombre()+" "+jugador.getApellidos());
                    nacionalidad.setText(jugador.getNacionalidad());


                    break;
                case 2:
                    row = inflater.inflate(R.layout.rowestadio,parent,false);
                    ViewHolderEstadio viewHolderEstadio = new ViewHolderEstadio(row);

                    ImageView imagenestadio = viewHolderEstadio.getImagenEstadio();
                    TextView nombreestadio= viewHolderEstadio.getNombre();
                    TextView capacidad = viewHolderEstadio.getCapacidad();
                    TextView ciudad = viewHolderEstadio.getCiudad();

                    Estadio estadio = (Estadio) getItem(position);
                    imagenestadio.setImageResource(estadio.getImagen());
                    nombreestadio.setText(estadio.getNombre());
                    capacidad.setText(estadio.getCapacidad());
                    ciudad.setText(estadio.getCiudad());

                    break;
            }
        }






        return(row);
    }

    @Override
    public int getItemViewType(int position){
        int fila;
        Equipo equipo=new Equipo();
        Jugador jugador=new Jugador();

        if(getItem(position).getClass()==equipo.getClass()){
            fila=0;
        }
        else if(getItem(position).getClass()==jugador.getClass()){
            fila=1;
        }
        else{
            fila=2;
        }

        return (fila);
    }
    @Override
    public int getViewTypeCount(){
        return 3;
    }

}













