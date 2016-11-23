package com.iesnervion.dleal.fragmentssimple;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements OnListadoColoresSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Si es movil.
        if(findViewById(R.id.container) != null){

            //Si hay algun estado de la instancia guardado
            if(savedInstanceState != null){

                return;

            }

            //Creamos la instancia del fragment
            ListadoColores firstFragment = new ListadoColores();

            //en el caso de que la actividad llegara con instrucciones especiales desde un intent
            //le insertamos los argumentos recogiendo el intent.
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, firstFragment).commit();

        }

    }

    @Override
    public void OnColourSelected(int position){
        // The user selected the headline of an article from the HeadlinesFragment

        // Capture the article fragment from the activity layout
        /*ArticleFragment articleFrag = (ArticleFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if (articleFrag != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            articleFrag.updateArticleView(position);*/



        }

}
