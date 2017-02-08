package com.iesnervion.dleal.appfebrerobar.Utilidades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.ListadoProductos;
import com.iesnervion.dleal.appfebrerobar.model.Producto;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Barsqlbbdd.*;
import java.util.List;

/**
 * Created by dleal on 8/02/17.
 */

public class Utilidades {
    private SQLiteDatabase db;
    public Utilidades(Context context) {
        BarTrackerDatabaseHelper bbdd = new BarTrackerDatabaseHelper(context);
        db = bbdd.getReadableDatabase();
    }

    public Producto getProductoxid(int id){

        Producto p = null;
        int numerofilas;
        //Cursor result= db.rawQuery("SELECT Count(*) FROM "+Productos.PRODUCTOS_TABLE_NAME,null);
        Cursor result= db.rawQuery("SELECT "+ Productos._ID+","+ Productos.PRODUCTO_NOMBRE+","+Productos.PRODUCTO_PRECIO+","+Productos.PRODUCTO_IDCATEGORIA+" FROM "+Productos.PRODUCTOS_TABLE_NAME+" WHERE "+Productos._ID+" = "+id,null);
        if(result.moveToFirst()){

            p = new Producto(result.getInt(0), result.getInt(3), result.getString(1), result.getDouble(2));
         //numerofilas= result.getInt(0);

        }

        result.close();

        return p;
    }
}
