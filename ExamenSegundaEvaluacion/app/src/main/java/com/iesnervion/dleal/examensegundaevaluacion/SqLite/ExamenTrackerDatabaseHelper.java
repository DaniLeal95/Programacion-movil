package com.iesnervion.dleal.examensegundaevaluacion.SqLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.iesnervion.dleal.examensegundaevaluacion.SqLite.BbddNombres.*;

/**
 * Created by dleal on 8/02/17.
 */

public class ExamenTrackerDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bar_tracker.db";
    private static final int DATABASE_VERSION = 1;
    //Vamos a implementar el patrón Singleton, para asegurarnos de que todos los hilos que intenten acceder a la BD
    //van a usar la misma instancia (la cual será única). Más info en el fichero SINGLETON-BD.txt
    private static ExamenTrackerDatabaseHelper instanciaHelper = null;
    private Context context=null;

    public synchronized static ExamenTrackerDatabaseHelper getInstance (Context context){
        if (instanciaHelper==null) {
            instanciaHelper = new ExamenTrackerDatabaseHelper(context.getApplicationContext());
        }
        return instanciaHelper;
    }


    public ExamenTrackerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Personas.PERSONAS_TABLE_NAME+" ("
                + Personas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Personas.PERSONAS_NOMBRE + " TEXT,"
                + Personas.PERSONAS_EDAD + " INTEGER,"
                + Personas.PERSONAS_TELEFONO + " TEXT,"
                + Personas.PERSONAS_SEXO + " TEXT"
                + ");");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion){
        android.util.Log.w("Constants", "Upgrading database, which will destroy allold data");
        db.execSQL("DROP TABLE IF EXISTS " + Personas.PERSONAS_TABLE_NAME);

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion,
                            int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }








}
