package com.iesnervion.dleal.examensegundaevaluacion.SqLite;

import android.provider.BaseColumns;



/**
 * Created by dleal on 8/02/17.
 */

public final class BbddNombres {
    private BbddNombres() {}

    public static final class Personas implements BaseColumns {
        private Personas() {}
        public static final String PERSONAS_TABLE_NAME="table_Personas";
        public static final String PERSONAS_NOMBRE="persona_nombre";
        public static final String PERSONAS_EDAD="persona_edad";
        public static final String PERSONAS_TELEFONO="persona_telefono";
        public static final String PERSONAS_SEXO="persona_sexo";
    }




}
