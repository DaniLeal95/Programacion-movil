package com.iesnervion.dleal.appfebrerobar.Utilidades;

import android.provider.BaseColumns;

import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;

/**
 * Created by dleal on 8/02/17.
 */

public final class Barsqlbbdd {
    private Barsqlbbdd() {}

    public static final class Productos implements BaseColumns {
        private Productos() {}
        public static final String PRODUCTOS_TABLE_NAME="table_producto";
        public static final String PRODUCTO_NOMBRE="producto_nombre";
        public static final String PRODUCTO_PRECIO="producto_precio";
        public static final String PRODUCTO_IDCATEGORIA="producto_IdCategoria";
    }

    public static final class Categoria implements BaseColumns {
        private Categoria() {}
        public static final String CATEGORIA_TABLE_NAME="table_categoria";
        public static final String CATEGORIA_NOMBRE="categoria_nombre";
    }

    public static final class DetallesCuentas implements BaseColumns{
        private DetallesCuentas(){}
        public static final String DETALLES_CUENTA_TABLE_NAME="table_detalles_cuenta";
        public static final String DETALLES_CUENTA_IDCUENTA="idcuenta";
        public static final String DETALLES_CUENTA_IDPRODUCTO="producto_id";
        public static final String DETALLES_CUENTA_CANTIDAD="cantidad";
    }

    public static final class Cuentas implements BaseColumns{
        private Cuentas(){}
        public static final String CUENTA_TABLE_NAME="table_cuenta";
        public static final String CUENTA_NUMMESA="num_mesa";
        public static final String CUENTA_PRECIOFINAL="precio_final";
        public static final String CUENTA_FECHA="fecha";
        public static final String CUENTA_FINALIZADA="finalizada";
    }

}
