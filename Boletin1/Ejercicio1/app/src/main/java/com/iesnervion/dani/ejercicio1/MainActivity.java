package com.iesnervion.dani.ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sumar;
    private EditText numero1;
    private EditText numero2;
    private TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumar = (Button) findViewById(R.id.sumar);
        numero1= (EditText) findViewById(R.id.num1);
        numero2= (EditText) findViewById(R.id.num2);
        resultado=(TextView) findViewById(R.id.txtresultado);

        sumar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


                int suma=
                Integer.parseInt(numero1.getText().toString())+
                Integer.parseInt(numero2.getText().toString());

                String res=Integer.toString(suma);
                resultado.setText(res);

    }
}



