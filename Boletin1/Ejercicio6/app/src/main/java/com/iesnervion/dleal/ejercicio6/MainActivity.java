package com.iesnervion.dleal.ejercicio6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imagebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagebutton=(ImageButton)findViewById(R.id.btnImage);

        imagebutton.setTag(R.drawable.altavoz);
        imagebutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        /*ImageButton btnImage=(ImageButton)v;*/
       /* int tag=(int)btnImage.getTag();*/
        if((int)(v.getTag())==R.drawable.altavoz){
            imagebutton.setImageResource(R.drawable.altavozmute);
            v.setTag(R.drawable.altavozmute);
        }else{
            imagebutton.setImageResource(R.drawable.altavoz);
            v.setTag(R.drawable.altavoz);
        }


    }
}
