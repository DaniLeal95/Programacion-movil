package com.iesnervion.dani.ejercicio3;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnizq;
    private Button btndrch;
    private EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnizq=(Button) findViewById(R.id.btnizq);
        btndrch=(Button) findViewById(R.id.btndrch);
        texto=(EditText) findViewById(R.id.texto);

        btnizq.setOnClickListener(this);
        btndrch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnizq:
                texto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

                break;

            case R.id.btndrch:
                texto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                break;
        }
    }
}
