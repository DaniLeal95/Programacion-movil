package com.example.dani.listviewdueno;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dani on 06/12/2016.
 */

public class MiViewHolderdos {

    private TextView nombreperro;
    private ImageView imgperro;
    private TextView razaperro;
    public MiViewHolderdos(TextView nombreperro, ImageView imgperro, TextView razaperro){

        this.nombreperro=nombreperro;
        this.imgperro =imgperro;
        this.razaperro = razaperro;
    }


    public TextView getNombreperro() {
        return nombreperro;
    }

    public ImageView getImgperro() {
        return imgperro;
    }

    public TextView getRazaperro() {
        return razaperro;
    }
}
