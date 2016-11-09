package com.iesnervion.dleal.customspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private Vector<RedSocial>redessociales= new Vector<>(0,1);


    private Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        redessociales.add(new RedSocial("facebook",R.drawable.facebook));
        redessociales.add(new RedSocial("instagram", R.drawable.instagram));
        redessociales.add(new RedSocial("twitter",R.drawable.twitter));
        redessociales.add(new RedSocial("whatsapp",R.drawable.whatsapp));

        spin=(Spinner) findViewById(R.id.spinner);

        MiArrayAdapter adaptador = new MiArrayAdapter(this,R.layout.rowspinnercustom,redessociales);

        adaptador.setDropDownViewResource(R.layout.rowspinnercustom);

        spin.setAdapter(adaptador);


    }


}
