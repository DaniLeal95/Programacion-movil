package com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.R;
import com.iesnervion.dleal.appfebrerobar.Utilidades.OnSwipeTouchListener;
import com.iesnervion.dleal.appfebrerobar.customfont.Customfont;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
import com.iesnervion.dleal.appfebrerobar.model.Producto;


import java.util.List;

/**
 * Created by dleal on 31/01/17.
 */

public class MiarrayAdapterCuenta extends ArrayAdapter<DetallesCuenta> {

    private GestureDetector mGestureDetector;

    public MiarrayAdapterCuenta(Context context, int resource, List<DetallesCuenta> productos) {
        super(context, resource, productos);
    }

    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolderCuenta viewHolder;

        if (row==null){

            row = inflater.inflate(R.layout.cuenta,parent,false);

            //TODO aqui me quedo

            Customfont lbluds = (Customfont) row.findViewById(R.id.lblcuentaunidades);
            Customfont lbldescripcion  = (Customfont) row.findViewById(R.id.lblcuentadescripcion);
            Customfont lblpvp  = (Customfont) row.findViewById(R.id.lblcuentapvp);
            Customfont importe = (Customfont) row.findViewById(R.id.lblcuentapreciototal);
            ImageButton sumacantidad = (ImageButton) row.findViewById(R.id.sumacantidad);
            ImageButton restacantidad = (ImageButton) row.findViewById(R.id.restacantidad);

            viewHolder = new ViewHolderCuenta(lbluds,lbldescripcion,lblpvp,importe,sumacantidad,restacantidad);
            row.setTag(viewHolder);


        }
        else{
            viewHolder = (ViewHolderCuenta) row.getTag();
        }



        DetallesCuenta dc = getItem(position);
        double importe = dc.getProducto().getPrecio()*dc.getCantidad();

        importe= Math.floor(importe*100)/100;


        ImageButton sumacantidad = viewHolder.getSumacantidad();
        ImageButton restacantidad = viewHolder.getRestacantidad();
        Customfont lbluds = viewHolder.getUds();
        Customfont lbldescripcion  = viewHolder.getDescripcion();
        Customfont lblpvp  = viewHolder.getPvp();
        Customfont lblimporte = viewHolder.getImporte();

        lbluds.setText(String.valueOf(dc.getCantidad()));
        lbldescripcion.setText(dc.getProducto().getNombre());
        lblpvp.setText(dc.getProducto().getPrecio()+"€");
        lblimporte.setText(String.valueOf(importe)+"€");


        sumacantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),String.valueOf(getItem(position).getCantidad()),Toast.LENGTH_SHORT).show();
            }
        });
        restacantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),String.valueOf(getItem(position).getCantidad()),Toast.LENGTH_SHORT).show();
            }
        });


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
