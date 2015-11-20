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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Jawher B on 30/10/2015.
 */
public class Inscription_2_2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_2_2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText rep = (EditText) findViewById(R.id.editText_Reponse);
        Button button_inscri = (Button) findViewById(R.id.buttonInscri);
        Spinner spinner_question = (Spinner) findViewById(R.id.spinner_question);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.liste_question, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_question.setAdapter(adapter);

        TestVide(rep, getResources().getString(R.string.erreur_champvide));
        TestLonguer(rep, getResources().getString(R.string.erreurmdp_inf));

        button_inscri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TestAll() == "done") {

                    Intent myIntent = new Intent(Inscription_2_2.this, MainActivity.class);
                    startActivity(myIntent);

                } else {
                    CustomDialag(TestAll());
                }

            }
        });
    }


    public String TestAll() {
        String msg;
        EditText rep = (EditText) findViewById(R.id.editText_Reponse);
        Spinner quest = (Spinner) findViewById(R.id.spinner_question);

        if (quest.getSelectedItemPosition() == 0)

        {
            msg = "Choisir votre question de sécurité";
        } else if ((rep.getText().toString().length() < 4)) {
            msg = "4 caractères minimum pour votre réponse";
        } else {
            msg = "done";
        }

        return msg;

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


    public void CustomDialag(String msg) {
        // Create custom dialog object
        final Dialog dialog = new Dialog(Inscription_2_2.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.custom_dialog);
        // Set dialog title
        dialog.setTitle("Erreur");

        // set values for custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(msg);


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

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
