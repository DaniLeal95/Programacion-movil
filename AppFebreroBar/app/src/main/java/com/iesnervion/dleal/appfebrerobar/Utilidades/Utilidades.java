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
        List<Producto> bebidas = new ArrayList<>();
        Producto p= null;
        Cursor result = db.rawQuery("SELECT "+ Productos._ID+","+ Productos.PRODUCTO_NOMBRE+","+Productos.PRODUCTO_PRECIO+","+Productos.PRODUCTO_IDCATEGORIA+" FROM "+Productos.PRODUCTOS_TABLE_NAME+" WHERE "+Productos.PRODUCTO_IDCATEGORIA+" = "+idcategoria,null);

        if(result.moveToFirst()){

            p = new Producto(result.getInt(0),  result.getString(1), result.getDouble(2),result.getInt(3));
        }
        return bebidas;
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
    * Metodo que comprueba que un producto esta metido en una cuenta
    *
    * */
    public int comprobarProductoEnCuenta(int id){


        int cantidad=0;
        Cursor result= db.rawQuery("SELECT "+Cuentas.CUENTA_CANTIDAD+" FROM "+ Cuentas.CUENTA_TABLE_NAME+" WHERE "+ Cuentas.CUENTA_IDPRODUCTO+" = "+id,null);
        if(result.moveToFirst()){
            cantidad = result.getInt(0);
        }
        result.close();
        return cantidad;
    }

    /*
    *   Añadimos un nuevo producto o le añadimos cantidad si ese producto ya lo habia pedido
    */
    public void anadirNuevoProductoaComanda(Producto p,int cantidad){

        //Si el producto ya fue insertado en la cuenta anteriormente
        //le actualizamos su cantidad
        int cantidadenbbdd=0;

        cantidadenbbdd= comprobarProductoEnCuenta(p.getIdproducto());

        if(comprobarProductoEnCuenta(p.getIdproducto())>0){
            ContentValues valores=new ContentValues();
            valores.put(Cuentas.CUENTA_CANTIDAD,cantidadenbbdd+cantidad);
            String where = Cuentas.CUENTA_IDPRODUCTO+" ="+p.getIdproducto();
            db.update(Cuentas.CUENTA_TABLE_NAME,valores,where,null);


        }

        else{
            ContentValues valores=new ContentValues();
            valores.put(Cuentas.CUENTA_IDPRODUCTO,p.getIdproducto());
            valores.put(Cuentas.CUENTA_CANTIDAD,cantidad);
            db.insert(Cuentas.CUENTA_TABLE_NAME,null,valores);
        }
    }

    /*
    * Recogemos la Cuenta entera
    * */

    public List<DetallesCuenta> getCuenta(){
        List<DetallesCuenta> c = new ArrayList<>();

        String select = "SELECT "+ Cuentas.CUENTA_IDPRODUCTO+","+Productos.PRODUCTO_IDCATEGORIA+","+Productos.PRODUCTO_NOMBRE+","+Productos.PRODUCTO_IDCATEGORIA+","+ Cuentas.CUENTA_CANTIDAD
                        +" FROM "+Cuentas.CUENTA_TABLE_NAME+" as C"+
                            " INNER JOIN "+Productos.PRODUCTOS_TABLE_NAME+" as P" +
                                " ON C."+Cuentas.CUENTA_IDPRODUCTO+"= P."+Productos._ID;

        Cursor result= db.rawQuery(select,null);
        if(result.moveToFirst()){
            do {
                Producto p = new Producto(result.getInt(0), result.getString(2), result.getDouble(3), result.getInt(1));
                DetallesCuenta dc = new DetallesCuenta(p, result.getInt(4));
                c.add(dc);
            }while(result.moveToNext());
        }

        result.close();

        return c;
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
