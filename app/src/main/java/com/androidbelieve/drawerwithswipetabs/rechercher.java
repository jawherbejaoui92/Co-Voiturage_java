package com.androidbelieve.drawerwithswipetabs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class  rechercher extends Activity {

    EditText VillDep_et,VillArr_et;
    Button btn_rech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rechercher);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        VillDep_et = (EditText)findViewById(R.id.editTextVilleDep);
        VillArr_et = (EditText)findViewById(R.id.editTextVilleArr);

        TestVide(VillDep_et, getResources().getString(R.string.erreur_champvide));
        TestVide(VillArr_et, getResources().getString(R.string.erreur_champvide));
        TestLonguer(VillDep_et, getResources().getString(R.string.erreurmdp_inf));
        TestLonguer(VillArr_et, getResources().getString(R.string.erreurmdp_inf));

        btn_rech = (Button)findViewById(R.id.buttonRechercher);


        btn_rech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(rechercher.this, Liste_Recherche.class);
                rechercher.this.startActivity(myIntent);
            }
        });



    }




    public void TestLonguer(final EditText champ, final String msg) {

        champ.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (champ.getText().toString().length() < 4) {
                    champ.setError(msg.toString());

                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (champ.getText().toString().length() < 4) {
                    champ.setError(msg.toString());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (champ.getText().toString().length() < 4) {
                    champ.setError(msg.toString());

                }
            }
        });
    }

    public void TestVide(final EditText champ, final String msg) {

        champ.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (champ.getText().toString().length() == 0) {
                    champ.setError(msg.toString());

                }
            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (champ.getText().toString().length() == 0) {
                    champ.setError(msg.toString());

                }
            }


            @Override
            public void afterTextChanged(Editable s) {
                if (champ.getText().toString().length() == 0) {
                    champ.setError(msg.toString());

                }
            }

        });
    }


    public void onBackPressed() {

        android.os.Process.killProcess(android.os.Process.myPid());

        // This above line close correctly
    }


}
