package com.iesnervion.dleal.pruebaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dleal on 10/01/17.
 */

public class MyListAdapter extends ArrayAdapter {

        public MyListAdapter(Context context, int resource, List<Producto> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            View row = convertView;
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewHolderProducto viewHolder;

            if (row == null) {


                row = inflater.inflate(R.layout.celda, parent, false);
                TextView nombre = (TextView) row.findViewById(R.id.tituloProducto);
                ImageView imagen = (ImageView) row.findViewById(R.id.imagenProducto);


                viewHolder = new ViewHolderProducto(imagen,nombre);

                row.setTag(viewHolder);


            } else {

                viewHolder = (ViewHolderProducto) row.getTag();


            }


            Producto producto = (Producto) getItem(position);
            TextView titulo=  viewHolder.getTituloProducto();
            ImageView imagen =  viewHolder.getImagenProducto();


            titulo.setText(producto.getNombre());
            imagen.setImageResource(R.drawable.beer);

            return row;
        }


        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
    }

