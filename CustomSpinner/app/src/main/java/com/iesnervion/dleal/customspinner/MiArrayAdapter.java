package com.iesnervion.dleal.customspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by dleal on 9/11/16.
 */

public class MiArrayAdapter extends ArrayAdapter{

    public MiArrayAdapter(Context c, int row, Vector<RedSocial> redessociales){
        super(c,row,redessociales);

    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;


        if(row==null){
            LayoutInflater inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.rowspinnercustom,parent,false);

            ViewHolderSpin viewHolderSpin = new ViewHolderSpin(row);

            ImageView logo= viewHolderSpin.getLogo();
            TextView name= viewHolderSpin.getName();

            RedSocial redsocial = (RedSocial) getItem(position);

            logo.setImageResource(redsocial.getImagen());
            name.setText(redsocial.getNombre());

        }


        return row;
    }
    @Override
    public View getDropDownView(int position,View convertView, ViewGroup parent){
        return getView(position,convertView,parent);
    }

}
