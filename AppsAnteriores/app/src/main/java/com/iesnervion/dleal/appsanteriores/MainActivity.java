package com.iesnervion.dleal.appsanteriores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

        private GridView g;

        private Vector<App> apps=new Vector<>(0,1);

        @Override
        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.activity_main);


            //Rellenamos el vector;
            App calculadora=new App("com.iesnervion.dleal.myapplication",R.drawable.calculator2,"Calculadora");
            //App listView = new App("")
            apps.add(calculadora);



            g=(GridView) findViewById(R.id.grid);
            g.setAdapter(new MiArrayAdapter(this, R.layout.cell, apps));
            g.setOnItemClickListener(this);

        }


        @Override
        public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id){
                //App aplicacion=apps.elementAt(position);
                this.startNewActivity(this,"com.iesnervion.dleal.myapplication");
        }


    public void startNewActivity(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null) {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + packageName));
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    }
