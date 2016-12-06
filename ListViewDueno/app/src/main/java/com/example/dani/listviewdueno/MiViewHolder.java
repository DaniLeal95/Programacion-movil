package com.example.dani.listviewdueno;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dani on 06/12/2016.
 */

public class MiViewHolder {

    private TextView txtnombredueño;
    private TextView txttlfdueño;
    private TextView txtDireccion;



    public MiViewHolder(TextView txtnombredueño,TextView txttlfdueño, TextView txtDireccion){
        this.txtnombredueño= txtnombredueño;
        this.txttlfdueño = txttlfdueño;
        this.txtDireccion = txtDireccion;

    }


    public TextView getTxtnombredueño() {return txtnombredueño;}

    public TextView getTxttlfdueño() {
        return txttlfdueño;
    }

    public TextView getTxtDireccion() {
        return txtDireccion;
    }
}
