package com.iesnervion.dani.boletin5ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private static final String[] equipos={
            "Golden State Warriors","Los Angeles Lakers","Cleveland Cavaliers","New York Knicks","Chicago Bulls","San Antonio Spurs","Boston Celtics","Miami Heat",
            "Toronto Raptors", "Oklahoma City Thunder", "Houston Rockets", "Philadelphia 76ers","Los Angeles Clippers","Brooklyn Nets","Dallas Maverick","Minnesota Timberwolves",
            "New Orleans Pelicans","Sacramento Kings","Milwaukee Bucks","Indiana Pacers","Charlotte Homets","Portland Trail Blazers","Detroit Pistons","Phoenix Suns","Atlanta Hawks",
            "Utah Jazz","Washington Wizards","Memphis Grizzlies","Denver Nuggets","Orlando Magic"
    };
    private ArrayAdapter<String> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista=(ListView)findViewById(R.id.list);
        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,equipos);
        lista.setAdapter(adaptador);
    }



}
