package com.androidbelieve.drawerwithswipetabs;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

/**
 * Created by Sirine on 06/12/2015.
 */
public class Item_List_Recherche extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_recherche);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            TextView TV_VillDep = (TextView)findViewById(R.id.TV_VillDep);
            TextView TV_VillArr = (TextView)findViewById(R.id.TV_VillArr);
            TextView TV_DateDep = (TextView)findViewById(R.id.TV_DateDep);
            TextView TV_Heure = (TextView)findViewById(R.id.TV_Heure);
            TextView TV_NbPlace = (TextView)findViewById(R.id.TV_NbPlace);
            TextView TV_Description = (TextView)findViewById(R.id.TV_Description);
            TextView TV_Bagage = (TextView)findViewById(R.id.TV_Bagage);
            TextView TV_Animal = (TextView)findViewById(R.id.TV_Animal);
            TextView TV_Fume = (TextView)findViewById(R.id.TV_Fume);
            TextView TV_Etat = (TextView)findViewById(R.id.TV_Etat);



    }
}}
