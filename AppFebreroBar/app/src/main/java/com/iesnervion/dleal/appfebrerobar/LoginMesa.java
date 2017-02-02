package com.iesnervion.dleal.appfebrerobar;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.csmpl.androidlib.edittextmod.EditTextMod;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.Mesa;

import java.util.ArrayList;
import java.util.List;

public class LoginMesa extends AppCompatActivity implements View.OnClickListener{


    private Button btnasignarmesa;
    private EditTextMod clave;
    private EditTextMod nombre;

    private List<Mesa> mesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mesa);

        btnasignarmesa = (Button) findViewById(R.id.btnLoginMesa);
        btnasignarmesa.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));
        clave = (EditTextMod) findViewById(R.id.passwloginmesa);
        clave.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));

        mesas = ((Listados)getApplication()).getMesas();



        nombre = (EditTextMod) findViewById(R.id.txtnombre);
        nombre.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));








        btnasignarmesa.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        boolean clavevalida=false;
        Mesa m = null;

        if(!clave.getText().equals("")) {

            for(int i=0;i<mesas.size() && !clavevalida;i++){
                if(mesas.get(i).getCodigo().equals(clave.getText().toString())){
                    m=mesas.get(i);
                    clavevalida=true;
                }

            }

            if(clavevalida) {
                Intent i = new Intent(this, Principal.class);
                i.putExtra("nummesa", m.getNummesa());

                if(!nombre.getText().toString().equals("")){
                    i.putExtra("nombreCuenta",nombre.getText().toString());
                }

                startActivity(i);
            }
            else{
                Toast.makeText(this,"Este codigo no corresponde a ninguna mesa",Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(this,"Por favor, inserta el codigo para poder registrarte en la mesa",Toast.LENGTH_SHORT).show();
        }
    }






}
