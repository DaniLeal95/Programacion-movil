package com.iesnervion.dleal.appfebrerobar.Utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.iesnervion.dleal.appfebrerobar.model.Categoria;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
import com.iesnervion.dleal.appfebrerobar.model.Producto;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Barsqlbbdd.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dleal on 8/02/17.
 */

public class Utilidades {
    private SQLiteDatabase db;
    private Context context;
    public Utilidades(Context context) {
        this.context=context;
        BarTrackerDatabaseHelper bbdd = new BarTrackerDatabaseHelper(context);
        db = bbdd.getReadableDatabase();
    }

    public List<Producto> getProductosxCategoria(int idcategoria){
        List<Producto> productos = new ArrayList<>();
        Producto p= null;
        Cursor result = db.rawQuery("SELECT "+ Productos._ID+","+ Productos.PRODUCTO_NOMBRE+","+Productos.PRODUCTO_PRECIO+","+Productos.PRODUCTO_IDCATEGORIA+","+Productos.PRODUCTO_OPERATIVO+" FROM "+Productos.PRODUCTOS_TABLE_NAME+" WHERE "+Productos.PRODUCTO_IDCATEGORIA+" = "+idcategoria,null);

        if(result.moveToFirst()){

            do {
                p = new Producto(result.getInt(0), result.getString(1), result.getDouble(2), result.getInt(3),result.getInt(4));
                productos.add(p);
            }while(result.moveToNext());
        }
        return productos;
    }

    public List<Categoria> getCategorias(){
        List<Categoria> categorias = new ArrayList<>();
        Categoria c= null;
        Cursor result = db.rawQuery("SELECT "+ Categorias._ID+","+ Categorias.CATEGORIA_NOMBRE+","+Categorias.CATEGORIA_OPERATIVO+" FROM "+Categorias.CATEGORIA_TABLE_NAME,null);

        if(result.moveToFirst()){

            do {
                c = new Categoria(result.getInt(0), result.getString(1), result.getInt(2));
                categorias.add(c);
            }while(result.moveToNext());
        }
        return categorias;
    }


    public Producto getProductoxid(int id){

        Producto p = null;

        Cursor result= db.rawQuery("SELECT "+ Productos._ID+","+ Productos.PRODUCTO_NOMBRE+","+Productos.PRODUCTO_PRECIO+","+Productos.PRODUCTO_IDCATEGORIA+","+Productos.PRODUCTO_OPERATIVO+" FROM "+Productos.PRODUCTOS_TABLE_NAME+" WHERE "+Productos._ID+" = "+id,null);
        if(result.moveToFirst()){
            do {
                p = new Producto(result.getInt(0),  result.getString(1), result.getDouble(2),result.getInt(3),result.getInt(4));
            }while(result.moveToNext());
        }

        result.close();

        return p;
    }

    public int getcantidadxid(int idproducto){
        int cantidad=-1;

        Cursor result= db.rawQuery("SELECT "+ DetallesCuentasNuevaComanda.DETALLES_CUENTA_CANTIDAD+" FROM "+DetallesCuentasNuevaComanda.DETALLES_CUENTA_TABLE_NAME+" WHERE "+DetallesCuentasNuevaComanda.DETALLES_CUENTA_IDPRODUCTO+" = "+idproducto,null);
        if(result.moveToFirst()){
            do {
                cantidad = result.getInt(0);
            }while(result.moveToNext());
        }

        result.close();

        return cantidad;
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
        if(c!=null) {
            select = "SELECT P." + Productos._ID + "," + Productos.PRODUCTO_NOMBRE + "," + Productos.PRODUCTO_PRECIO + "," + Productos.PRODUCTO_IDCATEGORIA + "," + DetallesCuentas.DETALLES_CUENTA_CANTIDAD
                    + " FROM " + DetallesCuentas.DETALLES_CUENTA_TABLE_NAME + " as C" +
                    " INNER JOIN " + Productos.PRODUCTOS_TABLE_NAME + " as P" +
                    " ON C." + DetallesCuentas.DETALLES_CUENTA_IDPRODUCTO + "= P." + Productos._ID;


            List<DetallesCuenta> detalles = new ArrayList<>();
            result = db.rawQuery(select, null);

            if (result.moveToFirst()) {
                do {
                    Producto p = new Producto(result.getInt(0), result.getString(1), result.getDouble(2), result.getInt(3),result.getInt(4));
                    DetallesCuenta dc = new DetallesCuenta(p, result.getInt(4));
                    detalles.add(dc);
                } while (result.moveToNext());
            }

            c.setDetallesCuentas(detalles);

            result.close();
        }
        return c;
    }

    public void modificacantidadNuevoPedido(int cantidad,int idproducto){

        int cant=this.getcantidadxid(idproducto);

        ContentValues valores = new ContentValues();
        valores.put(DetallesCuentasNuevaComanda.DETALLES_CUENTA_CANTIDAD,cantidad+cant);
        String where = DetallesCuentasNuevaComanda.DETALLES_CUENTA_IDPRODUCTO+"="+idproducto;
        db.update(DetallesCuentasNuevaComanda.DETALLES_CUENTA_TABLE_NAME,valores,where,null);

    }


    public void eliminaproductoNuevaComandaxid(int idproducto){
        db.delete(DetallesCuentasNuevaComanda.DETALLES_CUENTA_TABLE_NAME,DetallesCuentasNuevaComanda.DETALLES_CUENTA_IDPRODUCTO+"="+idproducto,null);
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

    public void InsertarnuevoPedidoenComanda(DetallesCuenta dc){

            ContentValues valores = new ContentValues();

            valores.put(DetallesCuentasNuevaComanda.DETALLES_CUENTA_IDPRODUCTO,dc.getProducto().getIdproducto());
            valores.put(DetallesCuentasNuevaComanda.DETALLES_CUENTA_CANTIDAD,dc.getCantidad());

            db.insert(DetallesCuentasNuevaComanda.DETALLES_CUENTA_TABLE_NAME,null,valores);

    }

    public List<DetallesCuenta> getDetallesNuevaComanda(){
        List<DetallesCuenta> detalles= new ArrayList<>();



        String select = "SELECT P."+Productos._ID+","+Productos.PRODUCTO_NOMBRE+","+Productos.PRODUCTO_PRECIO+","+Productos.PRODUCTO_IDCATEGORIA+",sum("+ DetallesCuentasNuevaComanda.DETALLES_CUENTA_CANTIDAD
                +") FROM "+DetallesCuentasNuevaComanda.DETALLES_CUENTA_TABLE_NAME+" as C"+
                " INNER JOIN "+Productos.PRODUCTOS_TABLE_NAME+" as P" +
                " ON C."+DetallesCuentasNuevaComanda.DETALLES_CUENTA_IDPRODUCTO+"= P."+Productos._ID
                +" GROUP BY P."+ Productos._ID ;



        Cursor result= db.rawQuery(select,null);

        if(result.moveToFirst()){
            do {
                Producto p = new Producto(result.getInt(0), result.getString(1), result.getDouble(2), result.getInt(3),result.getInt(4));
                DetallesCuenta dc = new DetallesCuenta(p, result.getInt(4));
                detalles.add(dc);
            }while(result.moveToNext());
        }


        return detalles;

    }

    public int obtenerIDCuenta(){
        int idCuenta=-1;

        String select = " SELECT "+ Cuentas._ID+ " FROM "+ Cuentas.CUENTA_TABLE_NAME;
        Cursor result= db.rawQuery(select,null);

        if(result.moveToFirst()) {
            idCuenta = result.getInt(0);
        }

        return idCuenta;

    }

    public void borrarProductos(){
        db.delete(Productos.PRODUCTOS_TABLE_NAME,null,null);
        db.delete(Categorias.CATEGORIA_TABLE_NAME,null,null);
    }

    //Lo borra TO DO de la tabla Cuenta
    public void borrarCuenta(){
        db.delete(DetallesCuentas.DETALLES_CUENTA_TABLE_NAME,null,null);
        db.delete(Cuentas.CUENTA_TABLE_NAME,null,null);
    }

    public void BorrarComandaPedido(){
        db.delete(DetallesCuentasNuevaComanda.DETALLES_CUENTA_TABLE_NAME,null,null);
    }


    //COMPRUEBA CONEXION
    public boolean hasActiveInternetConnection() {

        boolean hayconexion = false;

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null){

                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    hayconexion = true;
                }
            }
        }
        return hayconexion;
    }
}

