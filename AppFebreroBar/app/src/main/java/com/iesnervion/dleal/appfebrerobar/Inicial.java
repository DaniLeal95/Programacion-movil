package com.iesnervion.dleal.appfebrerobar;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import gr.escsoft.michaelprimez.revealedittext.RevealEditText;

public class Inicial extends AppCompatActivity implements View.OnClickListener {
    Button pedirmesa;
    RevealEditText nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        pedirmesa = (Button) findViewById(R.id.btnpedirmesa);
        nombre = (RevealEditText) findViewById(R.id.txtnombre);

        pedirmesa.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,nombre.getText()+"eeeeee",Toast.LENGTH_LONG);
    }
}
