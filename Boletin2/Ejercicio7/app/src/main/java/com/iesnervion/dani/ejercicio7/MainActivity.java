package com.iesnervion.dani.ejercicio7;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private TextView texto;
    private CheckBox negrita;
    private CheckBox fgigante;
    private CheckBox fminuscula;
    private CheckBox rojo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto=(TextView) findViewById(R.id.texto);
        negrita=(CheckBox) findViewById(R.id.negrita);
        fgigante=(CheckBox) findViewById(R.id.fgigante);
        fminuscula=(CheckBox) findViewById(R.id.fminuscula);
        rojo=(CheckBox) findViewById(R.id.rojo);

        negrita.setOnCheckedChangeListener(this);
        fgigante.setOnCheckedChangeListener(this);
        fminuscula.setOnCheckedChangeListener(this);
        rojo.setOnCheckedChangeListener(this);


    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch(buttonView.getId()){
            case R.id.negrita:
                if(isChecked){
                    texto.setTypeface(null,Typeface.BOLD);
                }
                else{
                    texto.setTypeface(null,Typeface.NORMAL);
                }
                break;

            case R.id.fgigante:

                if(isChecked){
                    fminuscula.setChecked(false);
                    texto.setTextSize(80);

                }
                else{
                        texto.setTextSize(20);
                }

                break;
            case R.id.fminuscula:


                if(isChecked){
                    texto.setTextSize(5);
                    fgigante.setChecked(false);
                }
                else{
                    texto.setTextSize(20);
                }
                break;
            case R.id.rojo:
                if(isChecked){
                    texto.setTextColor(Color.RED);
                }
                break;

        }

    }
}
