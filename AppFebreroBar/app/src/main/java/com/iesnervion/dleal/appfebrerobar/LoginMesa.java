package com.iesnervion.dleal.appfebrerobar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class LoginMesa extends AppCompatActivity implements View.OnClickListener {

    Spinner spinnermesas;
    Button btnasignarmesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mesa);

        btnasignarmesa = (Button) findViewById(R.id.btnLoginMesa);

        List mesas=new ArrayList(0);
        mesas.add("Mesa 1");
        mesas.add("Mesa 2");
        mesas.add("Mesa 3");
        mesas.add("Mesa 4");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mesas);


        spinnermesas = (Spinner) findViewById(R.id.spinnerLoginMesa);

        spinnermesas.setAdapter(arrayAdapter);

        btnasignarmesa.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Principal.class);

        startActivity(i);
    }

    //TODO Para rellenar el spinner hay que hacer un get. a la api para que me diga las mesas libres.
    //TODO Y Hacer para el boton cuando clickee una llamada al verbo para comprobar la contraseña
}
