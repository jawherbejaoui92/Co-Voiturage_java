package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Jawher B on 30/10/2015.
 */
public class Inscription_1_2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_1_2);

        final Tester_champ test = new Tester_champ();

        final EditText email = (EditText) findViewById(R.id.editTextmail);
        EditText mdp = (EditText) findViewById(R.id.editTextmdp);
        EditText confirmermdp = (EditText) findViewById(R.id.editTextconfirmer_mdp);
        final EditText nom = (EditText) findViewById(R.id.editTextnom);
        EditText prenom = (EditText) findViewById(R.id.editTextprenom);
        RadioGroup radioGroupsex = (RadioGroup) findViewById(R.id.RadioGroupsex);
        RadioButton homme = (RadioButton) findViewById(R.id.radioButtonhomme);
        RadioButton femme = (RadioButton) findViewById(R.id.radioButtonfemme);
        EditText tel = (EditText) findViewById(R.id.editTexttelephone);
        EditText ville = (EditText) findViewById(R.id.editTextville);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button button_suivant = (Button) findViewById(R.id.buttonsuivant);

        button_suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CustomDialag();
                Intent myIntent = new Intent(Inscription_1_2.this, Inscription_2_2.class);
                startActivity(myIntent);
            }
        });

        test.TestVide(email, getResources().getString(R.string.erreur_champvide));
        test.TestVide(mdp, getResources().getString(R.string.erreur_champvide));
        test.TestVide(confirmermdp, getResources().getString(R.string.erreur_champvide));
        test.TestVide(nom, getResources().getString(R.string.erreur_champvide));
        test.TestVide(prenom, getResources().getString(R.string.erreur_champvide));
        test.TestVide(tel, getResources().getString(R.string.erreur_champvide));
        test.TestVide(ville, getResources().getString(R.string.erreur_champvide));

        test.TestMotdePasse(mdp, confirmermdp, getResources().getString(R.string.erreurmdp_identique), getResources().getString(R.string.erreur_inf));

        test.TestLonguer(email, getResources().getString(R.string.erreurmdp_inf));
        test.TestLonguer(nom, getResources().getString(R.string.erreurmdp_inf));
        test.TestLonguer(prenom,getResources().getString(R.string.erreurmdp_inf));
        test.TestLonguer(mdp,getResources().getString(R.string.erreurmdp_inf));
        test.TestLonguer(confirmermdp,getResources().getString(R.string.erreurmdp_inf));
        test.TestLonguer(ville,getResources().getString(R.string.erreurmdp_inf));

        test.TestLonguerTel(tel, getResources().getString(R.string.erreur_tel));

    }

/*
    public void CustomDialag() {
        // Create custom dialog object
        final Dialog dialog = new Dialog(Inscription_1_2.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.custom_dialog);
        // Set dialog title
        dialog.setTitle("Custom Dialog");

        // set values for custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText("Custom dialog Android example.");
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.warning);

        dialog.show();

        Button declineButton = (Button) dialog.findViewById(R.id.buttonOk);
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });

        dialog.show();

    }*/
}


