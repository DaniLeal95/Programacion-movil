package com.iesnervion.dleal.appfebrerobar;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.MiarrayAdapterCuenta;
import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.ViewHolderCuenta;
import com.iesnervion.dleal.appfebrerobar.Callbacks.PostNuevaComandaCallback;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IBar;
import com.iesnervion.dleal.appfebrerobar.Utilidades.OnSwipeTouchListener;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.customfont.Customfont;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.id.list;

public class NuevaComanda extends ListActivity implements View.OnClickListener{

    private Cuenta c = null;
    private Customfont lblprecio;
    private Button realizarPedido,seguirComprando;
    private NuevaComanda main = this;
    private int nummesa;
    private boolean hayconexion;
    private AlertDialog dialog;
    private MiarrayAdapterCuenta miarrayAdapterCuenta;
    private List<DetallesCuenta> detalles;
    private Utilidades utilidades;

    private int idProductoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_comanda);

    utilidades=  new Utilidades(this);
        lblprecio =(Customfont) findViewById(R.id.lblprecionewComanda);

        realizarPedido = (Button) findViewById(R.id.btnrealizarPedido);
        realizarPedido.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));
        seguirComprando = (Button) findViewById(R.id.btnseguircomprando);
        seguirComprando.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));

        Intent intent= getIntent();

        Bundle bundle=intent.getExtras();

        this.nummesa= bundle.getInt("nummesa");



        final Utilidades u = new Utilidades(this);
        detalles=u.getDetallesNuevaComanda();

        double preciofinal=0.0;

        for(int i =0 ;i<detalles.size();i++){
            preciofinal+=(detalles.get(i).getProducto().getPrecio()*detalles.get(i).getCantidad());
        }

        preciofinal= Math.floor(preciofinal*100)/100;
        lblprecio.setText(""+preciofinal+"€");


        miarrayAdapterCuenta = new MiarrayAdapterCuenta(this,R.layout.cuenta,detalles, new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                //SUMAR UNA CANTIDAD
               RelativeLayout rl =(RelativeLayout) v.getParent();

                TextView lblid = (TextView) rl.findViewById(R.id.idNewCuenta);
                int id = Integer.parseInt((String) lblid.getText());
                utilidades.modificacantidadNuevoPedido(1,id);
                detalles=utilidades.getDetallesNuevaComanda();
                actualizavista();
            }
        },new View.OnClickListener(){
            @Override
            public void onClick(android.view.View v) {
                //RESTAR UNA CANTIDAD

                RelativeLayout rl =(RelativeLayout) v.getParent();

                TextView lblid = (TextView) rl.findViewById(R.id.idNewCuenta);
                idProductoSeleccionado = Integer.parseInt((String) lblid.getText());
                if(utilidades.getcantidadxid(idProductoSeleccionado)>1) {

                    utilidades.modificacantidadNuevoPedido(-1, idProductoSeleccionado);
                    detalles = utilidades.getDetallesNuevaComanda();
                    actualizavista();
                }

                else{
                    dialog = new AlertDialog.Builder(main)
                            .setTitle("Eliminar Prodructo")
                            .setMessage("De verdad desea eliminar el producto de tu pedido?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    utilidades.eliminaproductoNuevaComandaxid(idProductoSeleccionado);
                                    detalles = utilidades.getDetallesNuevaComanda();
                                    actualizavista();
                                }
                            })
                            .setNegativeButton("No",null)
                            .create();

                    dialog.show();
                }



                }
            }
        );


        setListAdapter(miarrayAdapterCuenta);

        realizarPedido.setOnClickListener(this);
        seguirComprando.setOnClickListener(this);

    }


    public void actualizavista(){

        miarrayAdapterCuenta.clear();

        miarrayAdapterCuenta.addAll(detalles);
        miarrayAdapterCuenta.notifyDataSetChanged();

        double preciofinal=0.0;

        for(int i =0 ;i<detalles.size();i++){
            preciofinal+=(detalles.get(i).getProducto().getPrecio()*detalles.get(i).getCantidad());
        }

        preciofinal= Math.floor(preciofinal*100)/100;
        lblprecio.setText(""+preciofinal+"€");



    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnrealizarPedido:

                Utilidades u = new Utilidades(main);
                hayconexion= u.hasActiveInternetConnection();
                //Llamada a la api

                if(hayconexion)dialogConfirmarPedido();
                else {
                    dialog = new AlertDialog.Builder(main)
                            .setTitle("Conexion fallida")
                            .setMessage("Por favor, revisa tu conexion.")
                            .setNeutralButton("Ok",null)
                            .create();

                    dialog.show();
                }

                break;

            case R.id.btnseguircomprando:
                Intent i = new Intent(v.getContext(),Carta.class);
                i.putExtra("cartaSeleccionable",true);
                i.putExtra("nummesa",nummesa);
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
                u.BorrarComandaPedido();

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
                u.BorrarComandaPedido();
                Intent i  =new Intent(dialogView.getContext(),Principal.class);
                i.putExtra("nummesa",nummesa);
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

        /*Gson gson = new GsonBuilder()
                .setLenient()
                .create();*/


        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Utilidades u = new Utilidades(this.main);
        List<DetallesCuenta> dc = u.getDetallesNuevaComanda();


        Cuenta c = new Cuenta(-1,-1,dc,"",0.0,0);
        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.postDetallesCuenta(base64,u.obtenerIDCuenta(),c).enqueue(cuentaCallback);
    }
    public String codifica64() {

        String credentials = "user:user";
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        //String auth ="Basic dXNlcjp1c2Vy";
        return auth;
    }


    public void CambiaIntent(){
        Intent i  =new Intent(this,Principal.class);
        i.putExtra("nummesa",nummesa);
        startActivity(i);
    }


}
