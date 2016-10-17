package com.iesnervion.dani.ejercicio2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button botonRojo;
    private Button botonVerde;
    private Button botonAzul;
    private EditText texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botonRojo=(Button) findViewById(R.id.botonrojo);
        botonAzul=(Button) findViewById(R.id.botonverde);
        botonVerde=(Button) findViewById(R.id.botonverde);
        texto=(EditText) findViewById(R.id.texto);

        botonRojo.setOnClickListener(this);
        botonVerde.setOnClickListener(this);
        botonAzul.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.botonrojo:
                texto.setTextColor(getResources().getColor(R.color.rojo));

                break;
            case R.id.botonazul:
                texto.setTextColor(getResources().getColor(R.color.azul));
                break;
            case R.id.botonverde:
                texto.setTextColor(getResources().getColor(R.color.verde));
                break;
        }

    }
}
