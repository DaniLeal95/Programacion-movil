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
            App listanba=new App("com.iesnervion.dleal.customlistview",R.drawable.nba,"ListView Nba");
            App twitter = new App ("com.twitter.android",R.drawable.twitter,"Twitter");

            apps.add(calculadora);
            apps.add(listanba);
            apps.add(twitter);



            g=(GridView) findViewById(R.id.grid);
            g.setAdapter(new MiArrayAdapter(this, R.layout.cell, apps));
            g.setOnItemClickListener(this);

        }


        @Override
        public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id){
                App aplicacion=apps.elementAt(position);
                this.startNewActivity(this,aplicacion.getUrl());
        }


    public void startNewActivity(Context context, String packageName) {
        // SI es una app android
       if(packageName.contains("com.")) {
           Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
           //Si el intent es null, es que no a encontrado ninguna app con ese package
           //entonces la buscara en el market
           if (intent == null) {
               // Bring user to the market or let them choose an app?
               intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("market://details?id=" + packageName));
           }
           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           context.startActivity(intent);
           //Si es una web
       }else{
           Intent i = new Intent(Intent.ACTION_VIEW);
           i.setData(Uri.parse(packageName));
           context.startActivity(i);
       }
    }

    }
