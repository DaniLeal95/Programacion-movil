package com.iesnervion.dleal.customspinner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iesnervion.dleal.customspinner.R;


/**
 * Created by dleal on 9/11/16.
 */

public class ViewHolderSpin {

    //Atributos
    private ImageView logo;
    private TextView name;

    //Constructor
    public ViewHolderSpin(View v){
        logo = (ImageView) v.findViewById(R.id.logo);
        name = (TextView) v.findViewById(R.id.name);
    }

    //Getters&Setters

    public ImageView getLogo() {
        return logo;
    }

    public void setLogo(ImageView logo) {
        this.logo = logo;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }
}
