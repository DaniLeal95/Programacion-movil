package com.iesnervion.dleal.appfebrerobar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.csmpl.androidlib.edittextmod.EditTextMod;
import com.iesnervion.dleal.appfebrerobar.Utilidades.BarTrackerDatabaseHelper;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.ListadoProductos;


public class Inicial extends AppCompatActivity implements View.OnClickListener {
    Button pedirmesa,vercarta;

    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: CAMBIAR ESTO POR LA LLAMADA A LA API
        ListadoProductos lp = new ListadoProductos();

        BarTrackerDatabaseHelper bbdd = new BarTrackerDatabaseHelper(this);
        //db = bbdd.getWritableDatabase();
        bbdd.insertCarta(lp.getProductos());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        1);
            }
        }

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
