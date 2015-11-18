package com.androidbelieve.drawerwithswipetabs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static com.androidbelieve.drawerwithswipetabs.R.id.editTextconfirmer_mdp;

/**
 * Created by Jawher B on 30/10/2015.
 */
public class Inscription_1_2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_1_2);

        Button button_suivant = (Button) findViewById(R.id.buttonsuivant);
        EditText email = (EditText) findViewById(R.id.editTextmail);
        EditText mdp = (EditText) findViewById(R.id.editTextmdp);
        EditText confirmermdp = (EditText) findViewById(editTextconfirmer_mdp);
        EditText nom = (EditText) findViewById(R.id.editTextnom);
        EditText prenom = (EditText) findViewById(R.id.editTextprenom);
        RadioGroup radioGroupsex = (RadioGroup) findViewById(R.id.RadioGroupsex);
        RadioButton homme = (RadioButton) findViewById(R.id.radioButtonhomme);
        RadioButton femme = (RadioButton) findViewById(R.id.radioButtonfemme);
        EditText tel = (EditText) findViewById(R.id.editTexttelephone);
        EditText ville = (EditText) findViewById(R.id.editTextville);
        DatePicker datenaiss = (DatePicker) findViewById(R.id.datePickernaissance);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        button_suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TestAll() == "done") {
                    Intent myIntent = new Intent(Inscription_1_2.this, Inscription_2_2.class);
                    startActivity(myIntent);
                } else {
                    CustomDialag(TestAll());
                }
            }
        });

        TestMotdePasse(mdp, confirmermdp, getResources().getString(R.string.erreurmdp_identique));
        TestVide(email, getResources().getString(R.string.erreur_champvide));
        TestVide(mdp, getResources().getString(R.string.erreur_champvide));
        TestVide(nom, getResources().getString(R.string.erreur_champvide));
        TestVide(prenom, getResources().getString(R.string.erreur_champvide));
        TestVide(tel, getResources().getString(R.string.erreur_champvide));
        TestVide(ville, getResources().getString(R.string.erreur_champvide));

        TestLonguer(nom, getResources().getString(R.string.erreurmdp_inf));
        TestLonguer(prenom, getResources().getString(R.string.erreurmdp_inf));
        TestLonguer(mdp, getResources().getString(R.string.erreurmdp_inf));
        TestLonguer(ville, getResources().getString(R.string.erreurmdp_inf));

        TestLonguerTel(tel, getResources().getString(R.string.erreur_tel));
        String strEmailAddress;

    }

    public String TestAll() {
        String msg;
        EditText email = (EditText) findViewById(R.id.editTextmail);
        EditText mdp = (EditText) findViewById(R.id.editTextmdp);
        EditText confirmermdp = (EditText) findViewById(editTextconfirmer_mdp);
        EditText nom = (EditText) findViewById(R.id.editTextnom);
        EditText prenom = (EditText) findViewById(R.id.editTextprenom);
        RadioGroup radioGroupsex = (RadioGroup) findViewById(R.id.RadioGroupsex);
        RadioButton homme = (RadioButton) findViewById(R.id.radioButtonhomme);
        RadioButton femme = (RadioButton) findViewById(R.id.radioButtonfemme);
        EditText tel = (EditText) findViewById(R.id.editTexttelephone);
        EditText ville = (EditText) findViewById(R.id.editTextville);

        if ((email.getText().toString().length() < 4) || (mdp.getText().toString().length() < 4) || (confirmermdp.getText().toString().length() < 4) || (nom.getText().toString().length() < 4) || (prenom.getText().toString().length() < 4) || (tel.getText().toString().length() < 4) || (ville.getText().toString().length() < 4)) {
            msg = "4 caractères minimum pour chaque champ";
        }

        else if (!(mdp.getText().toString().equals(confirmermdp.getText().toString()))) {
            msg = "Mot de passe non identiques";
        }

        else if ((tel.getText().toString().length() != 8)) {
            msg = "Numéro de téléphone invalide";
        }

        else {
            msg = "done";
        }

        return msg;

    }


    public void TestLonguer(final EditText champ, final String msg) {

        champ.addTextChangedListener(new TextWatcher() {
            Button button_suivant = (Button) findViewById(R.id.buttonsuivant);

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

    /******************************************************/

    public void TestVide(final EditText champ, final String msg) {

        champ.addTextChangedListener(new TextWatcher() {
            Button button_suivant = (Button) findViewById(R.id.buttonsuivant);

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

    /******************************************************/

    public void TestMotdePasse(final EditText champ1, final EditText champ2, final String msg) {

        champ2.addTextChangedListener(new TextWatcher() {
            Button button_suivant = (Button) findViewById(R.id.buttonsuivant);

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!(champ1.getText().toString().equals(champ2.getText().toString()))) {
                    champ2.setError(msg.toString());

                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(champ1.getText().toString().equals(champ2.getText().toString()))) {
                    champ2.setError(msg.toString());


                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(champ1.getText().toString().equals(champ2.getText().toString()))) {
                    champ2.setError(msg.toString());


                }
            }
        });
    }

    public void TestLonguerTel(final EditText champ, final String msg) {

        champ.addTextChangedListener(new TextWatcher() {
            Button button_suivant = (Button) findViewById(R.id.buttonsuivant);

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (champ.getText().toString().length() < 8) {
                    champ.setError(msg.toString());


                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (champ.getText().toString().length() < 8) {
                    champ.setError(msg.toString());

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (champ.getText().toString().length() < 8) {
                    champ.setError(msg.toString());

                }
            }
        });

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;

    }


    public void CustomDialag(String msg) {
        // Create custom dialog object
        final Dialog dialog = new Dialog(Inscription_1_2.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.custom_dialog);
        // Set dialog title
        dialog.setTitle("Erreur");

        // set values for custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(msg);
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_stat_name);

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

    }
}


