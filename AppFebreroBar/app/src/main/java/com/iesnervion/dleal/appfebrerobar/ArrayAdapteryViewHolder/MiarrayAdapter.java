package com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iesnervion.dleal.appfebrerobar.R;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.List;

/**
 * Created by dleal on 30/01/17.
 */

public class MiarrayAdapter extends ArrayAdapter<Producto> {
    public MiarrayAdapter(Context context, int resource, List<Producto> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolderProductos viewHolder;

        if (row==null){

            row = inflater.inflate(R.layout.filamenu,parent,false);

            com.iesnervion.dleal.appfebrerobar.customfont.Customfont lblnombre  = (com.iesnervion.dleal.appfebrerobar.customfont.Customfont) row.findViewById(R.id.lblnombremenu);
            com.iesnervion.dleal.appfebrerobar.customfont.Customfont lblprecio  = (com.iesnervion.dleal.appfebrerobar.customfont.Customfont) row.findViewById(R.id.lblpreciomenu);

            viewHolder = new ViewHolderProductos(lblnombre,lblprecio);
            row.setTag(viewHolder);


        }
        else{
            viewHolder = (ViewHolderProductos) row.getTag();
        }


        Producto producto = getItem(position);

        TextView lblnombre = viewHolder.getNombre();
        TextView lblprecio = viewHolder.getPrecio();

        lblnombre.setText(producto.getNombre());
        lblprecio.setText(""+producto.getPrecio()+"€");

        return row;
    }




    @Override
    public int getItemViewType(int position){
        return 0;
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }


}
