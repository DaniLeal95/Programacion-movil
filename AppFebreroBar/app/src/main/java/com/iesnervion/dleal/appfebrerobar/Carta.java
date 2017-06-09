package com.iesnervion.dleal.appfebrerobar;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;



import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.CustomExpandableListAdapter;

import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;


import com.iesnervion.dleal.appfebrerobar.model.Categoria;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;

import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




public class Carta extends AppCompatActivity implements View.OnClickListener {

    ExpandableListView explistview;

    private List<String> categorias;
    private List<Producto> fueradecarta;
    private List<Producto> bebidas;
    private List<Producto> tapasfrias;
    private List<Producto> tapascalientes;
    private List<Producto> productos;
    private HashMap <String,List<Producto>> hashMap;
    private ImageView anadiralacesta;
    private int nummesa;
    private boolean cartaseleccionable=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        anadiralacesta = (ImageView) findViewById(R.id.anadiralacesta);
        anadiralacesta.setVisibility(View.INVISIBLE);



        Intent i= getIntent();
        Bundle bundle=i.getExtras();
        if(bundle !=null){
            if(cartaseleccionable = bundle.getBoolean("cartaSeleccionable")) {
                Utilidades u = new Utilidades(this);
                //if(u.getCuenta().size()>0)
                //    anadiralacesta.setVisibility(View.VISIBLE);
            }

            if(bundle.getInt("nummesa")!=0){
                nummesa=bundle.getInt("nummesa");
            }
        }

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Carta");


        ExpandableListAdapter explistAdapter;


        getData();

        explistAdapter= new CustomExpandableListAdapter(this, categorias,hashMap,new View.OnClickListener() {

            @Override
            public void onClick(android.view.View v) {
                Utilidades u = new Utilidades(v.getContext());
                TextView lblid = (TextView) v.findViewById(R.id.idProductoCarta);
                int id = Integer.parseInt((String) lblid.getText());
                int cantidad = 1;
                Producto p = u.getProductoxid(id);

                DetallesCuenta dc = new DetallesCuenta(p,cantidad);
                u.InsertarnuevoPedidoenComanda(dc);

                Intent i = new Intent(v.getContext(),NuevaComanda.class);
                i.putExtra("nummesa",nummesa);
                startActivity(i);

            }

        },cartaseleccionable);
        explistview = (ExpandableListView) findViewById(R.id.expandableList);

        explistview.setAdapter(explistAdapter);

        anadiralacesta.setOnClickListener(this);



    }

    public void getData(){

        Utilidades u = new Utilidades(this);
        List<Categoria> categoriasmodel = u.getCategorias();
        categorias = new ArrayList<>();


        hashMap = new HashMap<>();
        productos = new ArrayList<>() ;

        for(int i = 0; i< categoriasmodel.size(); i++){
            categorias.add(categoriasmodel.get(i).getNombre());
            productos = u.getProductosxCategoria(categoriasmodel.get(i).getIdcategoria());
            hashMap.put((categorias.get(i)),productos);
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
        if(cartaseleccionable) {
            Intent i = new Intent(this, NuevaComanda.class);
            i.putExtra("nummesa",nummesa);
            startActivity(i);
        }else{
            Intent i = new Intent(this, Inicial.class);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {

         if(cartaseleccionable) {
            Utilidades u = new Utilidades(this);
            if (u.getDetallesNuevaComanda().size()> 0) {
                Intent i = new Intent(this, NuevaComanda.class);
                i.putExtra("nummesa",nummesa);
                startActivity(i);
            } else {
                Intent i = new Intent(this, Principal.class);
                i.putExtra("nummesa",nummesa);
                startActivity(i);
            }
        }
        else{
            Intent i = new Intent(this, Inicial.class);
            startActivity(i);
        }

    }

}
