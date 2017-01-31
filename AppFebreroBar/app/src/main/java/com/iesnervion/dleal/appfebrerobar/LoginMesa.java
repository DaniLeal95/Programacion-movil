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

import com.csmpl.androidlib.edittextmod.EditTextMod;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.Mesa;

import java.util.ArrayList;
import java.util.List;

public class LoginMesa extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spinnermesas;
    private Button btnasignarmesa;
    private EditTextMod clave;

    private int nummesa = -1;
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

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mesas);



        spinnermesas = (Spinner) findViewById(R.id.spinnerLoginMesa);

        //spinnermesas.setAdapter(arrayAdapter);

        spinnermesas.setOnItemSelectedListener(this);


        btnasignarmesa.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(nummesa!=-1) {
            Intent i = new Intent(this, Principal.class);
            i.putExtra("nummesa", nummesa);
            startActivity(i);
        }
        else{

        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //if(parent.getId()== spinnermesas.getId())
           // this.nummesa =Integer.parseInt(String.valueOf(this.mesas.get(position).charAt(mesas.get(position).length()-1))) ;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //TODO Para rellenar el spinner hay que hacer un get. a la api para que me diga las mesas libres.
    //TODO Y Hacer para el boton cuando clickee una llamada al verbo para comprobar la contraseña
}
