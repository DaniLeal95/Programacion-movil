package com.iesnervion.dleal.appfebrerobar.Permisos;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Dani on 09/02/2017.
 */

public class Permisos {
    private Activity miActividad;
    public Permisos(Activity thisActivity){

        miActividad=thisActivity;

    }

    public void getInternet(int requestCode){



            ActivityCompat.requestPermissions(miActividad,
                    new String[]{Manifest.permission.INTERNET},
                    requestCode);

    }
}
