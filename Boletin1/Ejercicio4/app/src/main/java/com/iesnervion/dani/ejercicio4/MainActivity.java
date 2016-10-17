package com.iesnervion.dani.ejercicio4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button anterior;
    private Button siguiente;
    private ImageView imagen;
    private int[] imagenes={R.drawable.balon1,R.drawable.balon2,R.drawable.balon3};
    private int imgactual=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        anterior=(Button)findViewById(R.id.atras);
        siguiente=(Button)findViewById(R.id.adelante);
        imagen=(ImageView)findViewById(R.id.imagen);

        anterior.setOnClickListener(this);
        siguiente.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.adelante:
                if(imgactual<2) {
                    imgactual++;
                    imagen.setImageResource(imagenes[imgactual]);
                }else{
                    imgactual=0;
                    imagen.setImageResource(imagenes[imgactual]);
                }

                break;
            case R.id.atras:
                if(imgactual>0) {
                    imgactual--;
                    imagen.setImageResource(imagenes[imgactual]);
                }else{
                    imgactual=2;
                    imagen.setImageResource(imagenes[imgactual]);
                }

                break;
        }

    }
}
