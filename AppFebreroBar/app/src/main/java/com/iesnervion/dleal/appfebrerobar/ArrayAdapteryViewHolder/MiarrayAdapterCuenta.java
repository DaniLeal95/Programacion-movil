package com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.R;
import com.iesnervion.dleal.appfebrerobar.Utilidades.OnSwipeTouchListener;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.customfont.Customfont;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
import com.iesnervion.dleal.appfebrerobar.model.Producto;


import java.util.List;

/**
 * Created by dleal on 31/01/17.
 */

public class MiarrayAdapterCuenta extends ArrayAdapter<DetallesCuenta> {

    private Utilidades utilidades;
    private DetallesCuenta dc;
    private View.OnClickListener listenermas;
    private View.OnClickListener listenermenos;
    private boolean cantidadmodificadora=false;

    public MiarrayAdapterCuenta(Context context, int resource, List<DetallesCuenta> productos,View.OnClickListener listenermas,View.OnClickListener listenermenos) {
        super(context, resource, productos);
        cantidadmodificadora = true;
        this.listenermas =listenermas;
        this.listenermenos = listenermenos;
    }
    public MiarrayAdapterCuenta(Context context, int resource, List<DetallesCuenta> productos) {
        super(context, resource, productos);
        cantidadmodificadora = false;
    }

    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewHolderCuenta viewHolder;


        if (row==null){

            row = inflater.inflate(R.layout.cuenta,parent,false);



            Customfont lbluds = (Customfont) row.findViewById(R.id.lblcuentaunidades);
            Customfont lbldescripcion  = (Customfont) row.findViewById(R.id.lblcuentadescripcion);
            Customfont lblpvp  = (Customfont) row.findViewById(R.id.lblcuentapvp);
            Customfont importe = (Customfont) row.findViewById(R.id.lblcuentapreciototal);
            TextView idinvisible = (TextView) row.findViewById(R.id.idNewCuenta);
            if(cantidadmodificadora) {
                ImageButton sumacantidad = (ImageButton) row.findViewById(R.id.sumacantidad);
                ImageButton restacantidad = (ImageButton) row.findViewById(R.id.restacantidad);
                viewHolder = new ViewHolderCuenta(lbluds,lbldescripcion,lblpvp,importe,sumacantidad,restacantidad,idinvisible);
            }
            else{
                ImageButton sumacantidad = null;
                ImageButton restacantidad = null;
                viewHolder = new ViewHolderCuenta(lbluds,lbldescripcion,lblpvp,importe,idinvisible);
                row.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,140));
            }



            row.setTag(viewHolder);


        }
        else{
            viewHolder = (ViewHolderCuenta) row.getTag();
        }


        utilidades = new Utilidades(row.getContext());
        dc = getItem(position);
        double importe = dc.getProducto().getPrecio()*dc.getCantidad();

        importe= Math.floor(importe*100)/100;


        ImageButton sumacantidad = viewHolder.getSumacantidad();
        ImageButton restacantidad = viewHolder.getRestacantidad();



        Customfont lbluds = viewHolder.getUds();
        Customfont lbldescripcion  = viewHolder.getDescripcion();
        Customfont lblpvp  = viewHolder.getPvp();
        Customfont lblimporte = viewHolder.getImporte();
        TextView idinvisible = viewHolder.getIdproducto();

        idinvisible.setText(String.valueOf(dc.getProducto().getIdproducto()));
        lbluds.setText(String.valueOf(dc.getCantidad()));
        lbldescripcion.setText(dc.getProducto().getNombre());
        lblpvp.setText(dc.getProducto().getPrecio()+"€");
        lblimporte.setText(String.valueOf(importe)+"€");


        if(cantidadmodificadora) {
            sumacantidad.setVisibility(View.VISIBLE);
            restacantidad.setVisibility(View.VISIBLE);

            sumacantidad.setOnClickListener(listenermas);
            restacantidad.setOnClickListener(listenermenos);

        }

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
