package com.iesnervion.dleal.daniexamenfragments;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iesnervion.dleal.daniexamenfragments.Fragments.OpcionesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment f = new OpcionesFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.contentPrincipal, f).commit();

    }


}
