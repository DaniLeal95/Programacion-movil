package com.iesnervion.dleal.appfebrerobar;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.Callbacks.ObtenerCuentaFragmentCallback;
import com.iesnervion.dleal.appfebrerobar.Callbacks.ObtenerCuentaPrincipalCallback;
import com.iesnervion.dleal.appfebrerobar.Fragments.BebidasFragment;
import com.iesnervion.dleal.appfebrerobar.Fragments.CuentaFragment;
import com.iesnervion.dleal.appfebrerobar.Fragments.TapasCalientesFragment;
import com.iesnervion.dleal.appfebrerobar.Fragments.TapasFriasFragment;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IBar;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BebidasFragment.OnFragmentInteractionListener, TapasCalientesFragment.OnFragmentInteractionListener,TapasFriasFragment.OnFragmentInteractionListener, CuentaFragment.OnFragmentInteractionListener{

    private FloatingActionButton fab;
    private Principal main = this;
    private Object o = new Object();
    private boolean getRecibido=false;

    private int nummesa;
    private Cuenta cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i= getIntent();

        Bundle bundle=i.getExtras();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilidades u = new Utilidades(main);

                main.getCuenta(u.getCuenta().getNummesa());
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        setTitle("Tu cuenta");
        this.nummesa= bundle.getInt("nummesa");


        Fragment f = new CuentaFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.content_principal, f).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {


            this.dialog();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment f=null;
        boolean fragmentoseleccionado = false;

/*
        if (id == R.id.itembebidas) {
            f = new BebidasFragment();
            fragmentoseleccionado = true;


        } else if (id == R.id.itemtapasfrias) {

            f = new TapasFriasFragment();
            fragmentoseleccionado = true;

        } else if (id == R.id.itemtapascalientes) {
            f= new TapasCalientesFragment();
            fragmentoseleccionado=true;

        }*/ if (id == R.id.iteminformation) {
            f= new BebidasFragment();
            fragmentoseleccionado=true;
        }
        else if(id == R.id.tucuenta){
            f= new CuentaFragment();
            fab.show();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_principal,f).commit();
        }


        if(fragmentoseleccionado){
            setTitle("Carta");
            fab.hide();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_principal,f).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public void dialog() {
        final AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = Principal.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popupcerrarapp, null);


        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //Principal.super.onDestroy();

                Intent i;
                i = new Intent(main, Inicial.class);
                i.putExtra("cerrar",true);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                main.startActivity(i);
                /*moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                main.finish();*/
            }
        });

        alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.setView(dialogView);
        alert.show();

    }

    public String codifica64() {

        String credentials = "user:user";
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        //String auth ="Basic dXNlcjp1c2Vy";
        return auth;
    }

    public void getCuenta(int nummesa){
        Retrofit retrofit;

        ObtenerCuentaPrincipalCallback adminCallback = new ObtenerCuentaPrincipalCallback(main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.getCuentaxmesa(nummesa,base64).enqueue(adminCallback);
    }

    public void cuentaActiva() {
        Intent i = new Intent(main,Carta.class);
        i.putExtra("cartaSeleccionable",true);
        i.putExtra("nummesa",nummesa);
        startActivity(i);
    }

    public void cuentaFinalizada() {
        Toast.makeText(this,"Cuenta finalizada",Toast.LENGTH_SHORT).show();

        Intent i;
        i = new Intent(main, Inicial.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        main.startActivity(i);
    }






}


