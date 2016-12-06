package com.example.dani.listviewdueno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class detailPersona extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private TextView nombre;
    private TextView id;
    private TextView telefono;
    private TextView direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_persona);

        Intent i=getIntent();
        Bundle bundle = i.getExtras();

        Dueño d =bundle.getParcelable(EXTRA_MESSAGE);

        nombre=(TextView)findViewById(R.id.nombrepersona);
        id=(TextView) findViewById(R.id.idpersona);
        telefono=(TextView) findViewById(R.id.telefonopersona);
        direccion=(TextView) findViewById(R.id.direccionpersona);

        nombre.setText(nombre.getText()+""+d.getNombre());
        id.setText(id.getText()+""+d.getId());
        telefono.setText(telefono.getText()+""+d.getTlf());
        direccion.setText(direccion.getText()+""+d.getDireccion());

    }
}
