package com.example.dani.listviewdueno;

import android.Manifest;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends ListActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    //PERMISOS PARA ESCRITURA
    static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listado l = new listado();
        Vector<Object> objetos = new Vector<>(0,1);

        objetos.addAll(l.getPerros());
        objetos.addAll(l.getDueños());

        setListAdapter(new MiArrayAdapter(this, R.layout.activity_main,objetos));

    }
    //PERMISO DE ESCRITURA
    public void verifyStoragePermissions(Activity activity) {
        //Comprueba si tiene permisos de escritura
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            //Si no los tiene se los pide al usuario
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    public void onListItemClick(ListView parent, View v,
                                int position, long id) {

        Intent info =null;
        if(this.getListAdapter().getItem(position) instanceof Dueño){
            info=new Intent(this, detailPersona.class);
            Dueño d= (Dueño) this.getListAdapter().getItem(position);
            info.putExtra(EXTRA_MESSAGE, d);

        }
        else{
            info=new Intent(this, detailPerro.class);
            Perro p= (Perro) this.getListAdapter().getItem(position);
            info.putExtra(EXTRA_MESSAGE, p);

        }

        startActivity(info);
    }


}
