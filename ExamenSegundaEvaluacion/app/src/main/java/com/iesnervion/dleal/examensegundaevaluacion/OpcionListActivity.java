package com.iesnervion.dleal.examensegundaevaluacion;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.iesnervion.dleal.examensegundaevaluacion.Fragments.DetailEditPersona;
import com.iesnervion.dleal.examensegundaevaluacion.Fragments.ListPersonas;
import com.iesnervion.dleal.examensegundaevaluacion.Fragments.OpcionesList;
import com.iesnervion.dleal.examensegundaevaluacion.SqLite.UtilidadesBBDD;
import com.iesnervion.dleal.examensegundaevaluacion.dummy.Persona;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Opciones. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,

 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class OpcionListActivity extends AppCompatActivity implements OnListadoOpcionSelected, OnListadoPersonasSelected{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    List<String> opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        UtilidadesBBDD utilidadesBBDD= new UtilidadesBBDD(this);

        Fragment f = new OpcionesList();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, f).commit();



        if (findViewById(R.id.opcion_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }



    @Override
    public void OnListadoOpcionSelected(int position)
    {

        if(position==0) {

            Fragment newFragment = new DetailEditPersona();

            this.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, newFragment).commit();
        }else{
            Fragment newFragment = new ListPersonas();

            this.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, newFragment).commit();
        }

    }


    @Override
    public void OnListadoPersonasSelected(int position) {

        UtilidadesBBDD u = new UtilidadesBBDD(this);
        List<Persona> personas=u.getPersonas();

        Persona p = personas.get(position);

        Fragment newFragment = new DetailEditPersona();

        //Aqui deberia meterle un paquete con la persona, pero no me da tiempo

        this.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, newFragment).commit();

    }
}
