package com.iesnervion.dleal.appsanteriores;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView g;
    private String type = "image/*";
    private String filename = "/storage/emulated/0/DCIM/Logo.png";
    private static final int SELECT_PICTURE = 1;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private String selectedImagePath;
    //String mediaPath = Environment.getExternalStorageDirectory() + filename;

    //createInstagramIntent(type, mediaPath);
    private Vector<App> apps = new Vector<>(0, 1);

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        this.verifyStoragePermissions(this);

        //Rellenamos el vector;
        App calculadora = new App("com.iesnervion.dleal.myapplication", R.drawable.calculator2, "Calculadora");
        App listanba = new App("com.iesnervion.dleal.layoutinflaterandviewholder", R.drawable.nba, "ListView Nba");
        App twitter = new App("com.twitter.android", R.drawable.twitter, "Twitter");
        App upPhotoInstagram = new App("addFoto",R.drawable.instagram,"Instagram");

        apps.add(calculadora);
        apps.add(listanba);
        apps.add(twitter);
        apps.add(upPhotoInstagram);


        g = (GridView) findViewById(R.id.grid);
        g.setAdapter(new MiArrayAdapter(this, R.layout.cell, apps));
        g.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id) {
        App aplicacion = apps.elementAt(position);
        this.startNewActivity(this, aplicacion.getUrl());
    }


    public void startNewActivity(Context context, String packageName) {
        // SI es una app android
        if (packageName.contains("com.")) {
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
            String mediaPath = Environment.getExternalStorageDirectory() + filename;

            createInstagramIntent(type, mediaPath);
        }

        /*else {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(packageName));
            context.startActivity(i);
        }*/
    }


        //
    private void createInstagramIntent(String type, String mediaPath) {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        String s  = "/storage/emulated/0/DCIM/Logo.png";
        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType("image/png");

        //Uri uri = Uri.fromFile(media);

        // Add the URI to the Intent.
        try {
            share.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),s,"Prueba desde android1","Prueba desde android2")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //share.setPackage("com.twitter.android");
        // Broadcast the Intent.
        startActivity(share);
        //startActivity(Intent.createChooser(share, "Share to"));
    }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public void verifyStoragePermissions(Activity activity)
    {
        //Comprueba si tiene permisos de escritura
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            //Si no los tiene se los pide al usuario
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }
}
