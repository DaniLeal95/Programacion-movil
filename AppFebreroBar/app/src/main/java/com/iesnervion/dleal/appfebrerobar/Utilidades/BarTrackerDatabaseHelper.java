package com.iesnervion.dleal.appfebrerobar.Utilidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iesnervion.dleal.appfebrerobar.Utilidades.Barsqlbbdd.*;
import com.iesnervion.dleal.appfebrerobar.model.Categoria;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
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

        db.execSQL("CREATE TABLE " + Categorias.CATEGORIA_TABLE_NAME+" ("
                + Categorias._ID + " INTEGER PRIMARY KEY,"
                + Categorias.CATEGORIA_NOMBRE + " TEXT,"
                + Categorias.CATEGORIA_OPERATIVO + " INTEGER"
                + ");");
        db.execSQL("CREATE TABLE " + Productos.PRODUCTOS_TABLE_NAME + " ("
                + Productos._ID + " INTEGER PRIMARY KEY ,"
                + Productos.PRODUCTO_NOMBRE + " TEXT,"
                + Productos.PRODUCTO_PRECIO + " DOUBLE,"
                + Productos.PRODUCTO_IDCATEGORIA + " INTEGER,"
                + Productos.PRODUCTO_OPERATIVO + " INTEGER,"
                + " FOREIGN KEY ("+Productos.PRODUCTO_IDCATEGORIA+") REFERENCES "+Categorias.CATEGORIA_TABLE_NAME+"("+Categorias._ID+")"
                + ");");

        db.execSQL("CREATE TABLE " + Cuentas.CUENTA_TABLE_NAME + " ("
                + Cuentas._ID + " INTEGER PRIMARY KEY,"
                + Cuentas.CUENTA_NUMMESA + " INTEGER,"
                + Cuentas.CUENTA_PRECIOFINAL + " INTEGER,"
                + Cuentas.CUENTA_FECHA+ " SMALLDATETIME,"
                + Cuentas.CUENTA_FINALIZADA+ " INTEGER);");

        db.execSQL("CREATE TABLE " + DetallesCuentas.DETALLES_CUENTA_TABLE_NAME + " ("
                + DetallesCuentas._ID + " INTEGER PRIMARY KEY  AUTOINCREMENT ,"
                + DetallesCuentas.DETALLES_CUENTA_IDCUENTA + " INTEGER,"
                + DetallesCuentas.DETALLES_CUENTA_IDPRODUCTO + " INTEGER,"
                + DetallesCuentas.DETALLES_CUENTA_CANTIDAD + " INTEGER,"
                + "FOREIGN KEY ("+ DetallesCuentas.DETALLES_CUENTA_IDCUENTA+") REFERENCES "+Cuentas.CUENTA_TABLE_NAME+"("+Cuentas._ID+"),"
                + "FOREIGN KEY ("+ DetallesCuentas.DETALLES_CUENTA_IDPRODUCTO+") REFERENCES "+Productos.PRODUCTOS_TABLE_NAME+"("+Productos._ID+")"
                + ");");

        db.execSQL("CREATE TABLE " + DetallesCuentasNuevaComanda.DETALLES_CUENTA_TABLE_NAME + " ("
                + DetallesCuentas._ID + " INTEGER PRIMARY KEY  AUTOINCREMENT ,"
                + DetallesCuentas.DETALLES_CUENTA_IDPRODUCTO + " INTEGER,"
                + DetallesCuentas.DETALLES_CUENTA_CANTIDAD + " INTEGER,"
                + "FOREIGN KEY ("+ DetallesCuentasNuevaComanda.DETALLES_CUENTA_IDPRODUCTO+") REFERENCES "+Productos.PRODUCTOS_TABLE_NAME+"("+Productos._ID+")"
                + ");");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion){
        android.util.Log.w("Constants", "Upgrading database, which will destroy allold data");
        db.execSQL("DROP TABLE IF EXISTS " + Cuentas.CUENTA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Productos.PRODUCTOS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Categorias.CATEGORIA_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion,
                            int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertProductos(List<Producto> productos){
        ContentValues valores ;
        SQLiteDatabase db = getWritableDatabase();
        //Insertamos los productos
        for(int i=0;i<productos.size();i++){
            valores=new ContentValues();
            Producto p = productos.get(i);
            if(p.getOperativo()==1) {
                valores.put(Productos._ID, p.getIdproducto());
                valores.put(Productos.PRODUCTO_NOMBRE, p.getNombre());
                valores.put(Productos.PRODUCTO_PRECIO, p.getPrecio());
                valores.put(Productos.PRODUCTO_IDCATEGORIA, p.getIdcategoria());
                valores.put(Productos.PRODUCTO_OPERATIVO, p.getOperativo());
                db.insert(Productos.PRODUCTOS_TABLE_NAME, null, valores);
            }
        }
    }
    public void insertCategorias(List<Categoria> categorias){
        ContentValues valores ;
        SQLiteDatabase db = getWritableDatabase();
        //Insertamos los productos
        for(int i=0;i<categorias.size();i++){
            if(categorias.get(i).getOperativo()==1) {
                valores = new ContentValues();
                Categoria p = categorias.get(i);
                valores.put(Categorias._ID, p.getIdcategoria());
                valores.put(Categorias.CATEGORIA_NOMBRE, p.getNombre());
                valores.put(Categorias.CATEGORIA_OPERATIVO, p.getOperativo());
                db.insert(Categorias.CATEGORIA_TABLE_NAME, null, valores);
            }
        }
    }

}
