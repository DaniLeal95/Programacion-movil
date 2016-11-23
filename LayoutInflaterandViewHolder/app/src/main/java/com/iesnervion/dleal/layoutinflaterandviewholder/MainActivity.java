package com.iesnervion.dleal.layoutinflaterandviewholder;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.Vector;

public class MainActivity extends ListActivity {

    private String[] equiposstring = {
            "Golden State Warriors", "Los Angeles Lakers", "Cleveland Cavaliers", "New York Knicks", "Chicago Bulls", "San Antonio Spurs", "Boston Celtics", "Miami Heat",
            "Toronto Raptors", "Oklahoma City Thunder", "Houston Rockets", "Philadelphia 76ers", "Los Angeles Clippers", "Brooklyn Nets", "Dallas Maverick", "Minnesota Timberwolves",
            "New Orleans Pelicans", "Sacramento Kings", "Milwaukee Bucks", "Indiana Pacers", "Charlotte Hornets", "Portland Trail Blazers", "Detroit Pistons", "Phoenix Suns", "Atlanta Hawks",
            "Utah Jazz", "Washington Wizards", "Memphis Grizzlies", "Denver Nuggets", "Orlando Magic"
    };
    private int[] imagenEquipos = {
            R.drawable.warriors,R.drawable.lakers,R.drawable.cavaliers,R.drawable.knicks,R.drawable.bulls,R.drawable.spurs,R.drawable.celtics,R.drawable.heat,
            R.drawable.raptors,R.drawable.thunder,R.drawable.rockets,R.drawable.sers,R.drawable.clippers,R.drawable.nets,R.drawable.mavericks,R.drawable.timberwolves,
            R.drawable.pelicans,R.drawable.kings,R.drawable.bucks,R.drawable.pacers,R.drawable.hornets,R.drawable.blazers,R.drawable.pistons,R.drawable.suns,R.drawable.hawks,
            R.drawable.jazz,R.drawable.wizards,R.drawable.grizzlies,R.drawable.nuggets,R.drawable.magic
    };

    private Jugador[] jugadores= { new Jugador("Michael","Jordan",R.drawable.jordan,"New York"),
                                   new Jugador("Kobe","Bryant",R.drawable.bryan,"Philadelphia"),
                                   new Jugador("Pau","Gasol",R.drawable.gasol,"Barcelona")};

    //private Vector<Jugador> jugadores = new Vector<>(0, 1);



    private Vector<Equipo> equipos = new Vector<>(0, 1);
    private Vector<Estadio> estadios = new Vector<>(0,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vector<Object> objetos= new Vector<>(0,1);

        for (int i = 0; i < equiposstring.length; i++) {
            equipos.add(new Equipo(equiposstring[i], imagenEquipos[i]));
        }
        for(int i= 0; i<equipos.size();i++){
            objetos.add(equipos.elementAt(i));
        }

        for(int i =0 ; i< jugadores.length;i++){
            objetos.add(jugadores[i]);
        }

        estadios.add(new Estadio("Chicago",22879,R.drawable.bullsstadium,"UNITED CENTER-CHICAGO BULLS"));
        objetos.add(estadios.elementAt(0));

        setListAdapter(new MiArrayAdapter(this, R.layout.rowequipo, R.id.label, objetos));
    }
    
}
