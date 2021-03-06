package com.iesnervion.dleal.appfebrerobar;


import android.content.Intent;
import android.graphics.Typeface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import android.widget.Button;

import android.widget.Toast;

import com.csmpl.androidlib.edittextmod.EditTextMod;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.iesnervion.dleal.appfebrerobar.Callbacks.MesasCallback;
import com.iesnervion.dleal.appfebrerobar.Callbacks.ObtenerCuentaLoginCallback;
import com.iesnervion.dleal.appfebrerobar.Callbacks.PostCuentaCallback;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IBar;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
import com.iesnervion.dleal.appfebrerobar.model.Mesa;


import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginMesa extends AppCompatActivity implements View.OnClickListener {



    private Button btnscan;
    private EditTextMod nombre;

    private String codigomesa="";

    private ZXingScannerView scanner;
    private List<Mesa> mesas;

    private Mesa m;
    private LoginMesa activity=this;

    private Cuenta cuenta;
    private int idgenerado;

    private boolean valido = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mesa);


        //Obtenemos las mesas de la api
        getMesas();



        btnscan = (Button) findViewById(R.id.btnscan);
        btnscan.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));


        nombre = (EditTextMod) findViewById(R.id.txtnombre);
        nombre.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));

        btnscan.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Utilidades utilidades = new Utilidades(v.getContext());

        switch (v.getId()) {


            case R.id.btnscan:


                IntentIntegrator integrator = new IntentIntegrator(activity);

                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Escanea el codigo que te ha dado el camarero");
                integrator.setCameraId(0);
                integrator.setOrientationLocked(false);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
            }
            else{



                codigomesa = result.getContents();


                for(int i = 0 ; i<mesas.size() && !valido;i++){
                    if(this.codigomesa.equals(mesas.get(i).getCodigo())){
                        valido=true;
                        this.m=mesas.get(i);
                    }
                }

                if(valido) {
                    Toast.makeText(this, "Codigo Escaneado", Toast.LENGTH_SHORT).show();






                        getCuenta(this.m.getNummesa());



                }
                else{
                    Toast.makeText(this, "Codigo ERRONEO", Toast.LENGTH_SHORT).show();
                }
            }
        }else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public String codifica64() {

        String credentials = "user:user";
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        //String auth ="Basic dXNlcjp1c2Vy";
        return auth;
    }

    public void getMesas(){
        Retrofit retrofit;

        MesasCallback adminCallback = new MesasCallback(this.activity);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.getMesas(base64).enqueue(adminCallback);
    }


    //METODOS DE LLAMADAS A LA API

    public void obtieneMesas(List<Mesa> mesas){
        this.mesas=mesas;
    }

    public void PostCuenta(){
        Retrofit retrofit;

        PostCuentaCallback cuentaCallback = new PostCuentaCallback(this.activity);



        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        Cuenta c = new Cuenta(0,this.m.getNummesa(),new ArrayList<DetallesCuenta>(), "",0.0,0);


        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.postCuenta(base64,c).enqueue(cuentaCallback);
    }





    //LLAMADA API

    public void getCuenta(int nummesa){
        Retrofit retrofit;

        ObtenerCuentaLoginCallback adminCallback = new ObtenerCuentaLoginCallback(activity);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.getCuentaxmesa(nummesa,base64).enqueue(adminCallback);
    }

    public void esperaCuenta() {
        Intent i = new Intent(this, Principal.class);
        i.putExtra("nummesa", this.m.getNummesa());

        if (!nombre.getText().toString().equals("")) {
            i.putExtra("nombreCuenta", nombre.getText().toString());
        }
        startActivity(i);
    }

    public void getCuentadespuesdepost(){

        this.getCuenta(this.m.getNummesa());
    }





}
