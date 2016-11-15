package com.iesnervion.dleal.appsanteriores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemClickListener {
        private TextView selection;
        private static final String[] items={"lorem", "ipsum", "dolor",
                "sit", "amet",
                "consectetuer", "adipiscing", "elit", "morbi", "vel",
                "ligula", "vitae", "arcu", "aliquet", "mollis",
                "etiam", "vel", "erat", "placerat", "ante",
                "porttitor", "sodales", "pellentesque", "augue", "purus"};
        private GridView g;

        @Override
        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.activity_main);
            selection=(TextView)findViewById(R.id.selection);

            g=(GridView) findViewById(R.id.grid);
            g.setAdapter(new MiArrayAdapter(this, R.layout.cell, items));
            g.setOnItemClickListener(this);
        }

    public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id) {
        selection.setText(items[position]);
    }

}
