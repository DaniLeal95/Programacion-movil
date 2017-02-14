package com.iesnervion.dleal.appfebrerobar;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.CustomExpandableListAdapter;
import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.MiarrayAdapterCuenta;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.customfont.Customfont;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
import com.iesnervion.dleal.appfebrerobar.model.ListadoProductos;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NuevaComanda extends ListActivity implements View.OnClickListener {

    private Cuenta c = null;
    private Customfont lblprecio;
    private Button realizarPedido,seguirComprando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_comanda);

        lblprecio =(Customfont) findViewById(R.id.lblprecionewComanda);

        realizarPedido = (Button) findViewById(R.id.btnrealizarPedido);
        realizarPedido.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));
        seguirComprando = (Button) findViewById(R.id.btnseguircomprando);
        seguirComprando.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));

        Utilidades u = new Utilidades(this);
        c =u.getCuenta();
       // c = new Cuenta(-1,-1,detallesCuenta);

        double preciofinal= Math.floor(c.getPreciofinal()*100)/100;
        lblprecio.setText(""+preciofinal+"€");
        setListAdapter(new MiarrayAdapterCuenta(this,R.layout.cuenta,c.getDetallesCuentas()));

        realizarPedido.setOnClickListener(this);
        seguirComprando.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnrealizarPedido:
                dialogConfirmarPedido();
                break;

            case R.id.btnseguircomprando:
                Intent i = new Intent(v.getContext(),Carta.class);
                i.putExtra("cartaSeleccionable",true);
                startActivity(i);

                break;
        }

    }

    public void dialogConfirmarPedido() {
        final AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = NuevaComanda.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.popupfinalizarpedido, null);


        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Utilidades u = new Utilidades(dialogView.getContext());
                u.finalizarPedido();
                Intent i  =new Intent(dialogView.getContext(),Principal.class);
                startActivity(i);
            }
        });

        alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.setView(dialogView);
        alert.show();

    }
    @Override
    public void onBackPressed() {
            this.dialogCancelarPedido();

    }

    private void dialogCancelarPedido() {
        final AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = NuevaComanda.this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.popupcancelarpedido, null);


        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Utilidades u = new Utilidades(dialogView.getContext());
                u.cancelarPedido();
                Intent i  =new Intent(dialogView.getContext(),Principal.class);
                startActivity(i);
            }
        });

        alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.setView(dialogView);
        alert.show();

    }
}
