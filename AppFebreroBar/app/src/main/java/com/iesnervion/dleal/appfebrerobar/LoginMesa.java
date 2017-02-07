package com.iesnervion.dleal.appfebrerobar;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import android.widget.Toast;

import com.csmpl.androidlib.edittextmod.EditTextMod;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.Mesa;


import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class LoginMesa extends AppCompatActivity implements View.OnClickListener, ZXingScannerView.ResultHandler {


    private Button btnasignarmesa;
    private Button btnscan;
    private EditTextMod nombre;

    private String codigomesa="";

    private ZXingScannerView scanner;
    private List<Mesa> mesas;

    private final Activity activity=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mesa);





        btnasignarmesa = (Button) findViewById(R.id.btnLoginMesa);
        btnasignarmesa.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));

        btnscan = (Button) findViewById(R.id.btnscan);
        btnscan.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));


        //clave = (EditTextMod) findViewById(R.id.passwloginmesa);
        //clave.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));

        mesas = ((Listados)getApplication()).getMesas();



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

                for (int i = 0; i < mesas.size() && !clavevalida; i++) {
                    if (mesas.get(i).getCodigo().equals(codigomesa.toString())) {
                        m = mesas.get(i);
                        clavevalida = true;
                    }
                }


                if (clavevalida) {
                    Intent i = new Intent(this, Principal.class);
                    i.putExtra("nummesa", m.getNummesa());

                    if (!nombre.getText().toString().equals("")) {
                        i.putExtra("nombreCuenta", nombre.getText().toString());
                    }

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
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);

                integrator.setOrientationLocked(true);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
                /*
                    scanner = new ZXingScannerView(this);


                    setContentView(scanner);
                    scanner.setResultHandler(this);
                    scanner.startCamera();*/
                break;
        }

    }


   @Override
    protected void onResume() {
        super.onResume();

       btnasignarmesa.setOnClickListener(this);
       btnscan.setOnClickListener(this);

    }

    @Override
    public void handleResult(Result result) {
        codigomesa = result.getText();
        Toast.makeText(this,"Codigo Escaneado , "+result.getText(),Toast.LENGTH_SHORT).show();
        scanner.stopCamera();

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

}







