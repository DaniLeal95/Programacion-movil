package com.iesnervion.dleal.appfebrerobar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.csmpl.androidlib.edittextmod.EditTextMod;


public class Inicial extends AppCompatActivity implements View.OnClickListener {
    Button pedirmesa;
    EditTextMod nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_inicial);

        pedirmesa = (Button) findViewById(R.id.btnpedirmesa);
        nombre = (EditTextMod) findViewById(R.id.txtnombre);

        pedirmesa.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!nombre.getText().toString().isEmpty()) {
            Intent i = new Intent(this, LoginMesa.class);
            i.putExtra("nombre", nombre.getText().toString());
            startActivity(i);
        }
        else{
            Toast.makeText(this,"Introduce un nombre",Toast.LENGTH_SHORT).show();
        }
    }
}
