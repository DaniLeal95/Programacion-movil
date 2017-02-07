package com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.iesnervion.dleal.appfebrerobar.R;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.Inflater;

/**
 * Created by Dani on 01/02/2017.
 */

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> tipos;
    private Map<String,List<Producto>> hashmap;


    public CustomExpandableListAdapter(Context context, List<String> tipos, Map<String, List<Producto>> hashmap) {
        this.context = context;
        this.tipos = tipos;
        this.hashmap = hashmap;
    }

    @Override
    public int getGroupCount() {
        return tipos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {


        return hashmap.get(tipos.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return tipos.get(groupPosition);

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hashmap.get(tipos.get(groupPosition)).get(childPosition);

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return hashmap.get(tipos.get(groupPosition)).get(childPosition).getIdproducto();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String tipo = (String) getGroup(groupPosition);

        if(convertView == null){
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.list_parent,null);

        }
        TextView lblNombreTipo = (TextView) convertView.findViewById(R.id.lblnombretipolistparent);
        lblNombreTipo.setTypeface(Typeface.createFromAsset(this.context.getAssets(),"fonts/EraserDust.ttf"));
        lblNombreTipo.setText(tipo);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Producto p =(Producto) getChild(groupPosition,childPosition);

        String nombreProducto = p.getNombre();
        String precio = String.valueOf(p.getPrecio());

        if(convertView == null){
            LayoutInflater inflate = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.list_child,null);

        }

        TextView lblNombreProducto = (TextView) convertView.findViewById(R.id.lblnombrelistchild);
        TextView lblprecio = (TextView) convertView.findViewById(R.id.lblpreciolistchild);

        lblNombreProducto.setText(nombreProducto);
        lblprecio.setText(precio+ " €");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
