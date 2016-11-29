package com.iesnervion.dleal.fragmentssimple;

import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by dleal on 23/11/16.
 */

public class Detail extends Fragment {

    //constante para referenciar al string "position"
    final static String POSITION = "position";

    //entero con la posicion actual.
    int mCurrentPosition = -1;

@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        if(savedInstance!= null){
            mCurrentPosition = savedInstance.getInt(POSITION);

        }

        View v = inflater.inflate(R.layout.detail,container,false);


        return v;

    }

    public void onStart(){
        super.onStart();

        //Recoge los argumentos
        Bundle args = getArguments();

        //si se ha cambiado de actividad a esta
        //ya sea cambiando de pantalla o girando la pantalla
        if(args != null){
            //Aqui iria el metodo de rellenar la pantalla con
            //los argumentos del bundle

            this.actualizaPantallaDetail(args.getInt(POSITION));
        }
        //Si el usuario ha pulsado otro objeto de la lista
        //pero no ha cambiado de actividad
        else if (mCurrentPosition != -1){
            //Aqui iria el metodo de rellenar la pantalla con
            //la posicion actual, por ejemplo en modo tablet
            //no cambia de actividad.
            this.actualizaPantallaDetail(mCurrentPosition);
        }
    }

    public void actualizaPantallaDetail(int position){
        LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.layout);
        TextView txtview = (TextView) getActivity().findViewById(R.id.textoDetalle);

        txtview.setText(ListadoColores.colors[position]);
        switch(ListadoColores.colors[position]) {
            case "Rojo":
                layout.setBackgroundColor(Color.RED);

            break;
            case "Verde":
                layout.setBackgroundColor(Color.GREEN);
                break;
            case "Azul":
                layout.setBackgroundColor(Color.BLUE);

                break;
            case "Amarillo":
                layout.setBackgroundColor(Color.YELLOW);
                break;
            case "Blanco":
                layout.setBackgroundColor(Color.WHITE);
                break;
            case "Negro":
                layout.setBackgroundColor(Color.BLACK);
                break;


        }



    }

    public static Detail newInstance(int position)
    {
        Detail fragment = new Detail();

        Bundle args = new Bundle();
        args.putInt(Detail.POSITION, position);
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        outState.putInt(POSITION, mCurrentPosition);
    }
}
