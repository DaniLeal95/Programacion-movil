package com.iesnervion.dleal.appfebrerobar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class LoginMesa extends AppCompatActivity {

    Spinner spinnermesas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mesa);

        List mesas=new ArrayList(0);
        mesas.add("Mesa 1");
        mesas.add("Mesa 2");
        mesas.add("Mesa 3");
        mesas.add("Mesa 4");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mesas);


        spinnermesas = (Spinner) findViewById(R.id.spinnerLoginMesa);

        spinnermesas.setAdapter(arrayAdapter);

    }

    //TODO Para rellenar el spinner hay que hacer un get. a la api para que me diga las mesas libres.
    //TODO Y Hacer para el boton cuando clickee una llamada al verbo para comprobar la contraseña
}
