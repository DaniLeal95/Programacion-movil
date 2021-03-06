package com.iesnervion.dani.boletin5ejercicio1;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

public class MainActivity extends ListActivity {

    //private ListView lista;
    private String[] equiposstring={
            "Golden State Warriors","Los Angeles Lakers","Cleveland Cavaliers","New York Knicks","Chicago Bulls","San Antonio Spurs","Boston Celtics","Miami Heat",
            "Toronto Raptors", "Oklahoma City Thunder", "Houston Rockets", "Philadelphia 76ers","Los Angeles Clippers","Brooklyn Nets","Dallas Maverick","Minnesota Timberwolves",
            "New Orleans Pelicans","Sacramento Kings","Milwaukee Bucks","Indiana Pacers","Charlotte Homets","Portland Trail Blazers","Detroit Pistons","Phoenix Suns","Atlanta Hawks",
            "Utah Jazz","Washington Wizards","Memphis Grizzlies","Denver Nuggets","Orlando Magic"
    };
    private int[] imagenEquipos = {
            R.drawable.warriors,R.drawable.lakers,R.drawable.cavaliers,R.drawable.knicks,R.drawable.bulls,R.drawable.spurs,R.drawable.celtics,R.drawable.heat,
            R.drawable.raptors,R.drawable.thunder,R.drawable.rockets,R.drawable.sers,R.drawable.clippers,R.drawable.nets,R.drawable.mavericks,R.drawable.timberwolves,
            R.drawable.pelicans,R.drawable.kings,R.drawable.bucks,R.drawable.pacers,R.drawable.hornets,R.drawable.blazers,R.drawable.pistons,R.drawable.suns,R.drawable.hawks,
            R.drawable.jazz,R.drawable.wizards,R.drawable.grizzlies,R.drawable.nuggets,R.drawable.magic
    };

    private Vector<Equipo> equipos=new Vector<>(0,1);
    private ArrayAdapter<String> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<equiposstring.length;i++){
            equipos.add(new Equipo(equiposstring[i],imagenEquipos[i]));
        }
        //lista=(ListView)findViewById(R.id.list);

        //setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,equipos));

        setListAdapter(new MiArrayAdapter(this,R.layout.fila_layout,R.id.nombre,equipos));
    }



}
