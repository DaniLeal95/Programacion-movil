package com.iesnervion.dleal.appfebrerobar.Utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iesnervion.dleal.appfebrerobar.Utilidades.Barsqlbbdd.*;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.List;

/**
 * Created by dleal on 8/02/17.
 */

public class BarTrackerDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bar_tracker.db";
    private static final int DATABASE_VERSION = 1;
    //Vamos a implementar el patrón Singleton, para asegurarnos de que todos los hilos que intenten acceder a la BD
    //van a usar la misma instancia (la cual será única). Más info en el fichero SINGLETON-BD.txt
    private static BarTrackerDatabaseHelper instanciaHelper = null;
    private Context context=null;

    public synchronized static BarTrackerDatabaseHelper getInstance (Context context){
        if (instanciaHelper==null) {
            instanciaHelper = new BarTrackerDatabaseHelper(context.getApplicationContext());
        }
        return instanciaHelper;
    }


    public BarTrackerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Categoria.CATEGORIA_TABLE_NAME+" ("
                + Categoria._ID + " INTEGER PRIMARY KEY,"
                + Categoria.CATEGORIA_NOMBRE + " TEXT"
                + ");");
        db.execSQL("CREATE TABLE " + Productos.PRODUCTOS_TABLE_NAME + " ("
                + Productos._ID + " INTEGER PRIMARY KEY ,"
                + Productos.PRODUCTO_NOMBRE + " TEXT,"
                + Productos.PRODUCTO_PRECIO + " DOUBLE,"
                + Productos.PRODUCTO_IDCATEGORIA + " INTEGERresult"
                + " , FOREIGN KEY ("+Productos.PRODUCTO_IDCATEGORIA+") REFERENCES "+Categoria.CATEGORIA_TABLE_NAME+"("+Categoria._ID+")"
                + ");");

        db.execSQL("CREATE TABLE " + Cuentas.CUENTA_TABLE_NAME + " ("
                + Cuentas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Cuentas.CUENTA_IDPRODUCTO + " INTEGER,"
                + Cuentas.CUENTA_CANTIDAD + " INTEGER"
                + " , FOREIGN KEY ("+ Cuentas.CUENTA_IDPRODUCTO+") REFERENCES "+Productos.PRODUCTOS_TABLE_NAME+"("+Productos._ID+")"
                + ");");


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion){
        android.util.Log.w("Constants", "Upgrading database, which will destroy allold data");
        db.execSQL("DROP TABLE IF EXISTS " + Cuentas.CUENTA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Productos.PRODUCTOS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Categoria.CATEGORIA_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion,
                            int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertCarta(List<Producto> productos){
        ContentValues valores = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();


        //Insertamos las Categorias
        valores.put(Categoria._ID,1);
        valores.put(Categoria.CATEGORIA_NOMBRE,"Bebidas");

        db.insert(Categoria.CATEGORIA_TABLE_NAME,null,valores);

        valores.put(Categoria._ID,2);
        valores.put(Categoria.CATEGORIA_NOMBRE,"Tapas Frias");

        db.insert(Categoria.CATEGORIA_TABLE_NAME,null,valores);

        valores.put(Categoria._ID,3);
        valores.put(Categoria.CATEGORIA_NOMBRE,"Tapas Calientes");

        db.insert(Categoria.CATEGORIA_TABLE_NAME,null,valores);


        //Insertamos los productos


        for(int i=0;i<productos.size();i++){
            valores=new ContentValues();
            Producto p = productos.get(i);
            valores.put(Productos._ID,p.getIdproducto());
            valores.put(Productos.PRODUCTO_NOMBRE,p.getNombre());
            valores.put(Productos.PRODUCTO_PRECIO,p.getPrecio());
            valores.put(Productos.PRODUCTO_IDCATEGORIA,p.getIdcategoria());
            db.insert(Productos.PRODUCTOS_TABLE_NAME,null,valores);

        }






    }
}
