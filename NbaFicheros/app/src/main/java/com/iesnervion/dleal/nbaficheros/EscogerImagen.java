package com.iesnervion.dleal.nbaficheros;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.iesnervion.dleal.nbaficheros.datos.ListadoJugadores;
import com.iesnervion.dleal.nbaficheros.model.Imagen;

import java.util.Vector;

public class EscogerImagen extends ListActivity
{


    Vector<Imagen> imagenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_imagen);



        imagenes= ((ListadoJugadores) getApplication()).getImagenes();
        Vector<Imagen> imgaux = new Vector<>(0,1);

        for(int i=0;i<imagenes.size();i++){
            if(!imagenes.elementAt(i).isEscogido()){
                imgaux.add(imagenes.elementAt(i));
            }
        }

        setListAdapter(new ArrayAdapterImagen(this,R.layout.imagen,imgaux));


    }



    @Override
    public void onListItemClick(ListView parent, View v,
                                int position, long id) {

        Intent i = new Intent();

        Imagen image=(Imagen)this.getListAdapter().getItem(position);

        int imagen = image.getImg();
        i.putExtra("Imagen",imagen);

        setResult(2,i);

        finish();
    }
}
