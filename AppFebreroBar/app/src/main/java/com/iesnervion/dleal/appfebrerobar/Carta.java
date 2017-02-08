package com.iesnervion.dleal.appfebrerobar;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.CustomExpandableListAdapter;
import com.iesnervion.dleal.appfebrerobar.Utilidades.BarTrackerDatabaseHelper;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.ListadoProductos;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;



public class Carta extends AppCompatActivity implements View.OnClickListener, ExpandableListView.OnChildClickListener {

    ExpandableListView explistview;

    private List<String> tipos;
    private List<Producto> fueradecarta;
    private List<Producto> bebidas;
    private List<Producto> tapasfrias;
    private List<Producto> tapascalientes;
    private HashMap <String,List<Producto>> hashMap;
    private ImageView anadiralacesta;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        anadiralacesta = (ImageView) findViewById(R.id.anadiralacesta);
        anadiralacesta.setVisibility(View.INVISIBLE);

        boolean cartaSeleccionable=false;
        Intent i= getIntent();
        Bundle bundle=i.getExtras();
        if(bundle !=null){
            cartaSeleccionable = bundle.getBoolean("cartaSeleccionable");
            anadiralacesta.setVisibility(View.VISIBLE);
        }

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Carta");

        ExpandableListAdapter explistAdapter;
        getData();

        explistAdapter= new CustomExpandableListAdapter(this,tipos,hashMap,new View.OnClickListener() {

            @Override
            public void onClick(android.view.View v) {
                Utilidades u = new Utilidades(v.getContext());
                TextView lblid = (TextView) v.findViewById(R.id.idProductoCarta);
                int id = Integer.parseInt((String) lblid.getText());

                Producto p = u.getProductoxid(id);

                Toast.makeText(v.getContext(),p.toString(),Toast.LENGTH_SHORT).show();

            }

        },cartaSeleccionable);
        explistview = (ExpandableListView) findViewById(R.id.expandableList);

        explistview.setAdapter(explistAdapter);





    }

    public void getData(){

        ListadoProductos lp = new ListadoProductos();
        tipos = new ArrayList<>();
        tipos.add("Bebidas");
        tipos.add("Tapas Frias");
        tipos.add("Tapas Calientes");
        tipos.add("Fuera de Carta");


        bebidas = lp.getBebidas();
        tapasfrias = lp.getTapasFrias();
        tapascalientes = lp.getTapasCalientes();
        fueradecarta = lp.getFueradeCarta();

        hashMap = new HashMap<>();

        for(int i = 0; i<tipos.size();i++){
            switch (i) {
                case 0:
                    hashMap.put(tipos.get(i),bebidas);
                    break;
                case 1:
                    hashMap.put(tipos.get(i),tapasfrias);
                    break;
                case 2:
                    hashMap.put(tipos.get(i),tapascalientes);
                    break;
                case 3:
                    hashMap.put(tipos.get(i),fueradecarta);
                    break;
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(this,"HOLA",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(this,"HOLAA",Toast.LENGTH_SHORT).show();

        return false;
    }
}
