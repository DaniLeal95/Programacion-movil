package com.iesnervion.dleal.appfebrerobar;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import android.widget.Button;

import android.widget.Toast;

import com.csmpl.androidlib.edittextmod.EditTextMod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.iesnervion.dleal.appfebrerobar.Callbacks.CuentaCallback;
import com.iesnervion.dleal.appfebrerobar.Callbacks.MesasCallback;
import com.iesnervion.dleal.appfebrerobar.Callbacks.PostCuentaCallback;
import com.iesnervion.dleal.appfebrerobar.Callbacks.ProductosCallback;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IBar;
import com.iesnervion.dleal.appfebrerobar.Utilidades.BarTrackerDatabaseHelper;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
import com.iesnervion.dleal.appfebrerobar.model.Mesa;


import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginMesa extends AppCompatActivity implements View.OnClickListener {


    private Button btnasignarmesa;
    private Button btnscan;
    private EditTextMod nombre;

    private String codigomesa="";

    private ZXingScannerView scanner;
    private List<Mesa> mesas;

    private LoginMesa activity=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mesa);


        //Obtenemos las mesas de la api
        getMesas();


        btnasignarmesa = (Button) findViewById(R.id.btnLoginMesa);
        btnasignarmesa.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));

        btnscan = (Button) findViewById(R.id.btnscan);
        btnscan.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));


        //clave = (EditTextMod) findViewById(R.id.passwloginmesa);
        //clave.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));

        nombre = (EditTextMod) findViewById(R.id.txtnombre);
        nombre.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));
        btnasignarmesa.setOnClickListener(this);
        btnscan.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        boolean clavevalida = false;
        Mesa m = null;

        switch (v.getId()) {

            case R.id.btnLoginMesa:
                if (!this.codigomesa.equals("")) {


                    for(int i = 0 ; i<mesas.size() || !clavevalida;i++){
                        if(this.codigomesa.equals(mesas.get(i).getCodigo())){
                            clavevalida=true;
                            m=mesas.get(i);
                        }
                    }

                if (clavevalida) {


                    Intent i = new Intent(this, Principal.class);
                    i.putExtra("nummesa", m.getNummesa());

                    if (!nombre.getText().toString().equals("")) {
                        i.putExtra("nombreCuenta", nombre.getText().toString());
                    }

                    this.PostCuenta(m.getNummesa());
                    startActivity(i);


                } else {
                    Toast.makeText(this, "Este codigo no corresponde a ninguna mesa", Toast.LENGTH_SHORT).show();
                }

                } else {
                 Toast.makeText(this, "Por favor, inserta el codigo para poder registrarte en la mesa", Toast.LENGTH_SHORT).show();
                }
                break;


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
                Toast.makeText(this,"Codigo Escaneado",Toast.LENGTH_SHORT).show();
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

    public void obtieneMesas(List<Mesa> mesas){
        this.mesas=mesas;
    }

    public void PostCuenta(int nummesa){
        Retrofit retrofit;

        PostCuentaCallback cuentaCallback = new PostCuentaCallback(this.activity);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        List<Cuenta> lista = new ArrayList<>();
        Cuenta c = new Cuenta(0,nummesa,new ArrayList<DetallesCuenta>());
        lista.add(c);

        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.postCuenta(base64,lista).enqueue(cuentaCallback);
    }



}
