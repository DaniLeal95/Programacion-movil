package com.iesnervion.dleal.appfebrerobar.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dani on 29/01/2017.
 */

public class ListadoProductos {

    private List<Producto> productos;

    public ListadoProductos(){
        Producto p= new Producto(1,1,"Cerveza",1.20);
        Producto p2= new Producto(2,1,"Tinto de Verano",1.00);
        Producto p3= new Producto(3,1,"Cocacola",1.50);
        Producto p4= new Producto(4,2,"Ensaladilla",2.00);
        Producto p5= new Producto(5,2,"Jamon",3.00);
        Producto p6= new Producto(6,3,"Alitas de pollo",2.50);
        Producto p7= new Producto(7,3,"Papas fritas",2.50);

        productos = new ArrayList<>();
        productos.add(p);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        productos.add(p5);
        productos.add(p6);
        productos.add(p7);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
