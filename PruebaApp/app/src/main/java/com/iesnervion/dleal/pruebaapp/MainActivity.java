package com.iesnervion.dleal.pruebaapp;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.etsy.android.grid.StaggeredGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private List<Producto> productos ;
    private RequestQueue queue;
    private StaggeredGridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        queue = Volley.newRequestQueue(this);
        productos = new ArrayList<>();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String url = "http://dleal.ciclo.iesnervion.es/Producto";

                JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>(){
                            @Override
                            public void onResponse(JSONArray response) {

                                RecibirJson(response);

                            }


                        }


                        ,new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(MainActivity.super.getApplicationContext(),"ERROR",Toast.LENGTH_SHORT);
                            }
                        })
                {@Override
                public Map< String, String > getHeaders() throws AuthFailureError {
                    HashMap< String, String > headers = new HashMap < String, String > ();
                    String encodedCredentials = Base64.encodeToString("passwordandlogin".getBytes(), Base64.NO_WRAP);
                    headers.put("Authorization", "Basic dXNlcjp1c2Vy");

                    return headers;
                }
                };
                    queue.add(arrayRequest);
            }
        });


        //queue.
        gridView= (StaggeredGridView) findViewById(R.id.grid_view);


        //productos.add(p);
        gridView.setAdapter(new MyListAdapter(this,R.layout.celda,productos));
    }

    @Override
    public void onResume(){
        super.onResume();


        gridView.setAdapter(new MyListAdapter(this,R.layout.celda,productos));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void RecibirJson(JSONArray jsonObject){

        for (int i = 0;i<jsonObject.length();i++){
            try {

                JSONObject o =(JSONObject) jsonObject.get(i);

                if(!o.getString("nombre").equals("null")) {
                    Producto p = new Producto(o.getInt("idproducto"), o.getString("tipo"), o.getInt("cantidad"), o.getString("nombre"));


                    productos.add(p);
                }




            } catch (JSONException e) {
                e.printStackTrace();
            }




        }
        onResume();


    }
}
