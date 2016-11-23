package com.iesnervion.dleal.fragmentssimple;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        }
        //Si el usuario ha pulsado otro objeto de la lista
        //pero no ha cambiado de actividad
        else if (mCurrentPosition != -1){
            //Aqui iria el metodo de rellenar la pantalla con
            //la posicion actual, por ejemplo en modo tablet
            //no cambia de actividad.
        }
    }
}
