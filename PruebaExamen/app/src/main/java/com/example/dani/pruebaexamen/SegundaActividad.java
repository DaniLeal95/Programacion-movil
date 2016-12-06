package com.example.dani.pruebaexamen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.dani.pruebaexamen.MainActivity.bundle;

public class SegundaActividad extends AppCompatActivity implements View.OnClickListener {

    TextView texto;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_actividad);

        texto = (TextView) findViewById(R.id.texto);
        btn=(Button) findViewById(R.id.btnenviar);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent();

        i.putExtra(bundle,texto.getText().toString());
        setResult(1,i);

        finish();
    }


}
