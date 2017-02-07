package com.iesnervion.dleal.appfebrerobar;

import android.app.ListActivity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.CustomExpandableListAdapter;
import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.MiarrayAdapterCuenta;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.ListadoProductos;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NuevaComanda extends ListActivity {
    private List<Producto> productos = null;
    Button pedido;
    TextView precio;
    ExpandableListView explistview;

    private List<String> tipos;
    private List<Producto> fueradecarta;
    private List<Producto> bebidas;
    private List<Producto> tapasfrias;
    private List<Producto> tapascalientes;
    private HashMap <String,List<Producto>> hashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_comanda);

        pedido = (Button) findViewById(R.id.btnnuevacomanda);
        pedido.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/EraserDust.ttf"));

        precio = (TextView) findViewById(R.id.lblprecionuevacomanda);


        Cuenta c = ((Listados)getApplication()).getCuenta();
        productos = c.getProductos();
        List<Producto> productosordenados = new ArrayList<>();
        List<Integer> cantidades = new ArrayList();

        //Droga dura que entiendo yo
        //Ordenamos el list
        for (int i=0;i<productos.size();i++){
            boolean encontrado = false;
            int uds=0;
            //Comprobamos que el producto a insertar no este insertado ya.
            for(int j=0;j< productosordenados.size()&& !encontrado;j++) {
                if(productosordenados.get(j).getIdproducto()==productos.get(i).getIdproducto()) {
                    encontrado = true;
                }
            }

            //Si no esta lo insertamos en el listOrdenado
            if(!encontrado){
                productosordenados.add(productos.get(i));



                //Ahora contamos las uds de cada producto
                for (int j=0;j<productos.size();j++){
                    if(productos.get(i).getIdproducto()==productos.get(j).getIdproducto()){
                        uds++;
                    }

                }


                cantidades.add(uds);
            }
        }


        double preciofinal= Math.floor(c.getPreciofinal()*100)/100;


        precio.setText(""+preciofinal+"€");

        setListAdapter(new MiarrayAdapterCuenta(this,R.layout.cuenta,productosordenados,cantidades));

        ExpandableListAdapter explistAdapter;
        getData();
        explistAdapter= new CustomExpandableListAdapter(this,tipos,hashMap);
        explistview = (ExpandableListView) findViewById(R.id.expandableListnuevaComanda);


        explistview.setAdapter(explistAdapter);



    }

    public void getData(){

        ListadoProductos lp = new ListadoProductos();
        tipos = new ArrayList<>();
        tipos.add("Bebidas");
        tipos.add("Tapas Frias");
        tipos.add("Tapas Calientes");
        tipos.add("Fuera de Carta");


        bebidas = lp.getBebidas();
        tapasfrias = lp.getTapasFrias();
        tapascalientes = lp.getTapasCalientes();
        fueradecarta = lp.getFueradeCarta();

        hashMap = new HashMap<>();

        for(int i = 0; i<tipos.size();i++){
            switch (i) {
                case 0:
                    hashMap.put(tipos.get(i),bebidas);
                    break;
                case 1:
                    hashMap.put(tipos.get(i),tapasfrias);
                    break;
                case 2:
                    hashMap.put(tipos.get(i),tapascalientes);
                    break;
                case 3:
                    hashMap.put(tipos.get(i),fueradecarta);
                    break;
            }
        }

    }
}
