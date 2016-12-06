package com.example.dani.listviewdueno;

import java.util.Vector;

/**
 * Created by Dani on 06/12/2016.
 */

public class listado {
    private Vector<Dueño> dueños ;
    private Vector<Perro> perros;

    public listado() {
        dueños = new Vector<>(0,1);
        perros = new Vector<>(0,1);

        Dueño d1= new Dueño("Ruiz","123456789",1,"En la mierda");
        Dueño d2= new Dueño("Alvaro","123456798",2,"En la ruina");
        Dueño d3= new Dueño("Dani","321654987",3,"Debajo del puente");
        Dueño d4= new Dueño("Dani","321654987",3,"Debajo del puente");
        Dueño d5= new Dueño("Dani","321654987",3,"Debajo del puente");
        Dueño d6= new Dueño("Dani","321654987",3,"Debajo del puente");
        Dueño d7= new Dueño("Dani","321654987",3,"Debajo del puente");



        dueños.add(d1);
        dueños.add(d2);
        dueños.add(d3);
        dueños.add(d4);
        dueños.add(d5);
        dueños.add(d6);
        dueños.add(d7);
        dueños.add(d3);





        Perro p1= new Perro("Paquito", android.R.drawable.ic_media_next,"Anormal",1);
        Perro p2= new Perro("Panchito", android.R.drawable.ic_media_next,"Retrasao",1);
        Perro p3= new Perro("Guarrito", android.R.drawable.ic_media_next,"Chochow",2);
        Perro p4= new Perro("Osvaldo" ,android.R.drawable.ic_media_next,"Gringo",3);
        Perro p5= new Perro("Plata o plomo", android.R.drawable.ic_media_next,"Narco",3);
        Perro p6= new Perro("TeReviento", android.R.drawable.ic_media_next,"Asesino",3);
        Perro p7= new Perro("TeReviento", android.R.drawable.ic_media_next,"Asesino",3);
        Perro p8= new Perro("TeReviento", android.R.drawable.ic_media_next,"Asesino",3);
        Perro p9= new Perro("TeReviento", android.R.drawable.ic_media_next,"Asesino",3);
        Perro p10= new Perro("TeReviento", android.R.drawable.ic_media_next,"Asesino",3);

        perros.add(p1);
        perros.add(p2);
        perros.add(p3);
        perros.add(p4);
        perros.add(p5);
        perros.add(p6);
        perros.add(p7);
        perros.add(p8);
        perros.add(p9);
        perros.add(p10);


    }

    public Vector<Dueño> getDueños() {
        return dueños;
    }

    public void setDueños(Vector<Dueño> dueños) {
        this.dueños = dueños;
    }

    public Vector<Perro> getPerros() {
        return perros;
    }

    public void setPerros(Vector<Perro> perros) {
        this.perros = perros;
    }
}
