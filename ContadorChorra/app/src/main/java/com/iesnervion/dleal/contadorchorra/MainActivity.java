package com.iesnervion.dleal.contadorchorra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CONTADOR = "";
    private int contador = 0;
    private TextView txtview;
    private Button suma;
    private Button muestra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtview = (TextView) findViewById( R.id.textView2);
        suma = (Button) findViewById( R.id.button);
        muestra = (Button) findViewById(R.id.button2);


        suma.setOnClickListener(this);
        muestra.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState (Bundle savedInstaceState){
        savedInstaceState.putInt(CONTADOR,contador);
    }

    @Override
    public void onRestoreInstanceState ( Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        contador = savedInstanceState.getInt(CONTADOR);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                contador++;
                break;
            case R.id.button2:
                txtview.setText(""+contador);
                break;
        }


    }
}
