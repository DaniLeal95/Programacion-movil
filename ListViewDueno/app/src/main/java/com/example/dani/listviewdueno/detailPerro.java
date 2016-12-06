package com.example.dani.listviewdueno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class detailPerro extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private TextView nombre;
    private TextView iddueno;
    private TextView raza;
    private ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_perro);

        Intent i=getIntent();
        Bundle bundle = i.getExtras();

        Perro p =bundle.getParcelable(EXTRA_MESSAGE);

        nombre=(TextView)findViewById(R.id.nombreperrodetail);
        iddueno=(TextView) findViewById(R.id.idduenodetail);
        raza=(TextView) findViewById(R.id.razaperrodetail);
        imagen=(ImageView) findViewById(R.id.imagen);

        nombre.setText(nombre.getText()+""+p.getNombre());
        iddueno.setText(iddueno.getText()+""+p.getIddueño());
        raza.setText(raza.getText()+""+p.getRaza());
        imagen.setImageResource(p.getImg());
    }
}
