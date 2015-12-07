package com.androidbelieve.drawerwithswipetabs;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;

/**
 * Created by Sirine on 05/12/2015.
 */
public class Liste_Recherche extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_recherche);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ListView listViewRecherche = (ListView) findViewById(R.id.listViewRecherche);


    }
    }
