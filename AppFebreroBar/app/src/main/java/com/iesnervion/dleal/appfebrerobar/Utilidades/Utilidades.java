package com.iesnervion.dleal.appfebrerobar.Utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
import com.iesnervion.dleal.appfebrerobar.model.Producto;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Barsqlbbdd.*;

import java.util.ArrayList;
import java.util.Date;
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

    public List<Producto> getProductosxCategoria(int idcategoria){
        List<Producto> productos = new ArrayList<>();
        Producto p= null;
        Cursor result = db.rawQuery("SELECT "+ Productos._ID+","+ Productos.PRODUCTO_NOMBRE+","+Productos.PRODUCTO_PRECIO+","+Productos.PRODUCTO_IDCATEGORIA+" FROM "+Productos.PRODUCTOS_TABLE_NAME+" WHERE "+Productos.PRODUCTO_IDCATEGORIA+" = "+idcategoria,null);

        if(result.moveToFirst()){

            do {
                p = new Producto(result.getInt(0), result.getString(1), result.getDouble(2), result.getInt(3));
                productos.add(p);
            }while(result.moveToNext());
        }
        return productos;
    }


    public Producto getProductoxid(int id){

        Producto p = null;

        Cursor result= db.rawQuery("SELECT "+ Productos._ID+","+ Productos.PRODUCTO_NOMBRE+","+Productos.PRODUCTO_PRECIO+","+Productos.PRODUCTO_IDCATEGORIA+" FROM "+Productos.PRODUCTOS_TABLE_NAME+" WHERE "+Productos._ID+" = "+id,null);
        if(result.moveToFirst()){
            do {
                p = new Producto(result.getInt(0),  result.getString(1), result.getDouble(2),result.getInt(3));
            }while(result.moveToNext());
        }

        result.close();

        return p;
    }



    /*
    * Recogemos la Cuenta entera
    * */

    public Cuenta getCuenta(){
        Cuenta c = null;

        String select = "SELECT "+ Cuentas._ID+","+Cuentas.CUENTA_NUMMESA+","+Cuentas.CUENTA_PRECIOFINAL+","+Cuentas.CUENTA_FECHA+","+ Cuentas.CUENTA_FINALIZADA
                        +" FROM "+Cuentas.CUENTA_TABLE_NAME;


        Cursor result= db.rawQuery(select,null);
        if(result.moveToFirst()){
            do {
                c = new Cuenta(result.getInt(0),result.getInt(1),new ArrayList<DetallesCuenta>(),result.getString(3),result.getDouble(2),result.getInt(4));
            }while(result.moveToNext());
        }



        //Producto p = new Producto(result.getInt(0), result.getString(2), result.getDouble(3), result.getInt(1));
        result.close();

        select = "SELECT "+Productos._ID+","+Productos.PRODUCTO_NOMBRE+","+Productos.PRODUCTO_PRECIO+","+Productos.PRODUCTO_IDCATEGORIA+","+ DetallesCuentas.DETALLES_CUENTA_CANTIDAD
                +" FROM "+DetallesCuentas.DETALLES_CUENTA_TABLE_NAME+" as C"+
                " INNER JOIN "+Productos.PRODUCTOS_TABLE_NAME+" as P" +
                " ON C."+DetallesCuentas.DETALLES_CUENTA_IDPRODUCTO+"= P."+Productos._ID;


        Cursor result2= db.rawQuery(select,null);
        List<DetallesCuenta> detalles=new ArrayList<>();
        if(result2.moveToFirst()){
            do {
                Producto p = new Producto(result.getInt(0), result.getString(1), result.getDouble(3), result.getInt(3));
                DetallesCuenta dc = new DetallesCuenta(p, result.getInt(4));
                detalles.add(dc);
            }while(result2.moveToNext());
        }

        c.setDetallesCuentas(detalles);

        result2.close();
        return c;
    }


    public void insertCuenta(Cuenta cuenta){
        ContentValues valores=new ContentValues();
        valores.put(Cuentas._ID,cuenta.getIdcuenta());
        valores.put(Cuentas.CUENTA_NUMMESA,cuenta.getNummesa());
        valores.put(Cuentas.CUENTA_PRECIOFINAL,cuenta.getPreciofinal());
        valores.put(Cuentas.CUENTA_FECHA,cuenta.getFecha());
        valores.put(Cuentas.CUENTA_FINALIZADA,cuenta.getFinalizada());
        db.insert(Cuentas.CUENTA_TABLE_NAME,null,valores);


        valores = new ContentValues();

        for(int i = 0;i<cuenta.getDetallesCuentas().size();i++){
            valores.put(DetallesCuentas.DETALLES_CUENTA_IDCUENTA,cuenta.getIdcuenta());
            valores.put(DetallesCuentas.DETALLES_CUENTA_IDPRODUCTO,cuenta.getDetallesCuentas().get(i).getProducto().getIdproducto());
            valores.put(DetallesCuentas.DETALLES_CUENTA_CANTIDAD,cuenta.getDetallesCuentas().get(i).getCantidad());
            db.insert(DetallesCuentas.DETALLES_CUENTA_TABLE_NAME,null,valores);

        }
    }

    //Lo borra TO DO de la tabla Cuenta
    public void finalizarPedido(){
        boolean borradoexitoso=false;

        //TODO : Aqui tendria primero que hacer una llamada a la api para ingresarle la comanda a la cuenta
        db.delete(Cuentas.CUENTA_TABLE_NAME,null,null);


    }

    //Lo borra TO DO de la tabla Cuenta
    public void cancelarPedido(){

        db.delete(Cuentas.CUENTA_TABLE_NAME,null,null);

    }
}
