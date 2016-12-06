package com.example.dani.pruebaexamen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String bundle= "BUNDLE";
    private TextView txto;
    private Button btnsiguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txto=(TextView) findViewById(R.id.textoquerecibo);
        btnsiguiente=(Button) findViewById(R.id.btnprincipal);

        btnsiguiente.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this,SegundaActividad.class);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==1)
        {
            String message=data.getStringExtra(bundle);
            txto.setText(message);
        }
    }
}
