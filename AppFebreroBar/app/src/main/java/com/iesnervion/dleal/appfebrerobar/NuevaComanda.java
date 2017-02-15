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
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.CustomExpandableListAdapter;
import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.MiarrayAdapterCuenta;
import com.iesnervion.dleal.appfebrerobar.Callbacks.PostCuentaCallback;
import com.iesnervion.dleal.appfebrerobar.Callbacks.PostNuevaComandaCallback;
import com.iesnervion.dleal.appfebrerobar.Callbacks.PrincipalCallBack;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IBar;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.customfont.Customfont;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
import com.iesnervion.dleal.appfebrerobar.model.ListadoProductos;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NuevaComanda extends ListActivity implements View.OnClickListener {

    private Cuenta c = null;
    private Customfont lblprecio;
    private Button realizarPedido,seguirComprando;
    private NuevaComanda main = this;


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
        List<DetallesCuenta> detalles=u.getDetallesNuevaComanda();

        double preciofinal=0.0;
        for(int i =0 ;i<detalles.size();i++){
            preciofinal+=(detalles.get(i).getProducto().getPrecio()*detalles.get(i).getCantidad());
        }

        preciofinal= Math.floor(preciofinal*100)/100;
        lblprecio.setText(""+preciofinal+"€");
        setListAdapter(new MiarrayAdapterCuenta(this,R.layout.cuenta,detalles));

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
                main.PostCuenta(u.obtenerIDCuenta());
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

    public void PostCuenta(int nummesa){
        Retrofit retrofit;

        PostNuevaComandaCallback cuentaCallback = new PostNuevaComandaCallback(this.main);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Utilidades u = new Utilidades(this.main);
        List<DetallesCuenta> dc = u.getDetallesNuevaComanda();

        try {


            JSONArray parent = new JSONArray();
            JSONObject last = new JSONObject();

            for (int i= 0;i<dc.size();i++){

                last.put("producto",dc.get(i).getProducto());
                last.put("cantidad",dc.get(i).getCantidad());
               //TODO  first.put()
            }

            //parent.put("listdetallecuenta",first);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Cuenta c = new Cuenta(-1,-1,dc,"",0.0,0);
        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        //adminInter.postDetallesCuenta(base64,c).enqueue(cuentaCallback);
    }
    public String codifica64() {

        String credentials = "user:user";
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        //String auth ="Basic dXNlcjp1c2Vy";
        return auth;
    }

    public void recogerCuentaApi(){
       Utilidades u = new Utilidades(this);
        getCuenta(u.obtenerIDCuenta());
    }

    public void getCuenta(int idcuenta){
        Retrofit retrofit;

        PrincipalCallBack adminCallback = new PrincipalCallBack(this.main);



        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.getCuenta(idcuenta,base64).enqueue(adminCallback);
    }
}
