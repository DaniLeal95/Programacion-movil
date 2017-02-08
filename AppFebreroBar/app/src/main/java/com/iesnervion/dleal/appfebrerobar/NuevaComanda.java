package com.iesnervion.dleal.appfebrerobar;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.CustomExpandableListAdapter;
import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.MiarrayAdapterCuenta;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.ListadoProductos;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NuevaComanda extends ListActivity {
    private List<Producto> productos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_comanda);


    }



}
