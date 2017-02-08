package com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iesnervion.dleal.appfebrerobar.R;
import com.iesnervion.dleal.appfebrerobar.customfont.Customfont;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
import com.iesnervion.dleal.appfebrerobar.model.Producto;


import java.util.List;

/**
 * Created by dleal on 31/01/17.
 */

public class MiarrayAdapterCuenta extends ArrayAdapter<DetallesCuenta> {

    public MiarrayAdapterCuenta(Context context, int resource, List<DetallesCuenta> productos) {
        super(context, resource, productos);
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolderCuenta viewHolder;

        if (row==null){

            row = inflater.inflate(R.layout.cuenta,parent,false);

            Customfont lbluds = (Customfont) row.findViewById(R.id.lblcuentaunidades);
            Customfont lbldescripcion  = (Customfont) row.findViewById(R.id.lblcuentadescripcion);
            Customfont lblpvp  = (Customfont) row.findViewById(R.id.lblcuentapvp);
            Customfont importe = (Customfont) row.findViewById(R.id.lblcuentapreciototal);

            viewHolder = new ViewHolderCuenta(lbluds,lbldescripcion,lblpvp,importe);
            row.setTag(viewHolder);


        }
        else{
            viewHolder = (ViewHolderCuenta) row.getTag();
        }



        DetallesCuenta dc = getItem(position);
        double importe = dc.getProducto().getPrecio()*dc.getCantidad();


        Customfont lbluds = viewHolder.getUds();
        Customfont lbldescripcion  = viewHolder.getDescripcion();
        Customfont lblpvp  = viewHolder.getPvp();
        Customfont lblimporte = viewHolder.getImporte();

        lbluds.setText(String.valueOf(dc.getCantidad()));
        lbldescripcion.setText(dc.getProducto().getNombre());
        lblpvp.setText(String.valueOf(dc.getProducto().getPrecio())+"€");
        lblimporte.setText(String.valueOf(importe)+"€");

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
