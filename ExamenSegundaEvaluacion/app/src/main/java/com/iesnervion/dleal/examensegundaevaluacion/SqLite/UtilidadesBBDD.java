package com.iesnervion.dleal.examensegundaevaluacion.SqLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.iesnervion.dleal.examensegundaevaluacion.SqLite.BbddNombres.*;

import com.iesnervion.dleal.examensegundaevaluacion.dummy.Persona;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dleal on 22/02/17.
 */

public class UtilidadesBBDD {

    private SQLiteDatabase db;
    private Context context;

    public UtilidadesBBDD(Context context) {
        this.context=context;
        ExamenTrackerDatabaseHelper bbdd = new ExamenTrackerDatabaseHelper(context);
        db = bbdd.getReadableDatabase();
    }

    public List<Persona> getPersonas(){

        List<Persona> personas = new ArrayList<>();
        Persona p= null;
        Cursor result = db.rawQuery("SELECT "
                + Personas._ID+","
                + Personas.PERSONAS_NOMBRE+","
                + Personas.PERSONAS_EDAD+","
                + Personas.PERSONAS_TELEFONO+","
                +Personas.PERSONAS_SEXO
                + " FROM "+ Personas.PERSONAS_TABLE_NAME,null);

        if(result.moveToFirst()){

            do {
                p = new Persona(result.getInt(0),result.getString(1), result.getInt(2), result.getString(3), result.getString(4).charAt(0));
                personas.add(p);
            }while(result.moveToNext());
        }
        return personas;
    }


    public long insertPersona(Persona p){
        long insertado=0;

        ContentValues valores = new ContentValues();

        valores.put(Personas.PERSONAS_NOMBRE,p.getNombre());
        valores.put(Personas.PERSONAS_EDAD,p.getEdad());
        valores.put(Personas.PERSONAS_TELEFONO,p.getTelefono());
        valores.put(Personas.PERSONAS_SEXO,String.valueOf(p.getSexo()));

        insertado=db.insert(Personas.PERSONAS_TABLE_NAME,null,valores);
        return insertado;
    }


    public long updatePersona(Persona p){
        long actualizado=0;

        ContentValues valores = new ContentValues();

        valores.put(Personas.PERSONAS_NOMBRE,p.getNombre());
        valores.put(Personas.PERSONAS_EDAD,p.getEdad());
        valores.put(Personas.PERSONAS_TELEFONO,p.getTelefono());
        valores.put(Personas.PERSONAS_SEXO,String.valueOf(p.getSexo()));

        actualizado= db.update(Personas.PERSONAS_TABLE_NAME,valores,Personas._ID+" = "+p.getId(),null);

        return actualizado;
    }

}
