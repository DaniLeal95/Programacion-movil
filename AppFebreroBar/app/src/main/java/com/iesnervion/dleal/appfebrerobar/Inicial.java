package com.iesnervion.dleal.appfebrerobar;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.csmpl.androidlib.edittextmod.EditTextMod;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;


public class Inicial extends AppCompatActivity implements View.OnClickListener {
    Button pedirmesa,vercarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Listados l = new Listados();

        setContentView(R.layout.activity_inicial);

        pedirmesa = (Button) findViewById(R.id.btnpedirmesa);
        vercarta = (Button) findViewById(R.id.btnverCarta);

        pedirmesa.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));
        vercarta.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));

        pedirmesa.setOnClickListener(this);
        vercarta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i=null;
        switch(v.getId()) {
            case R.id.btnpedirmesa:
                 i= new Intent(this, LoginMesa.class);

                break;
            case R.id.btnverCarta:
                 i = new Intent(this,Carta.class);
        }

        startActivity(i);
    }
}
