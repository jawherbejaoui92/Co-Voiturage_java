package com.androidbelieve.drawerwithswipetabs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Ratan on 7/29/2015.
 */
public class Fragment_recherche extends Fragment {
    EditText VillDep_et, VillArr_et;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.connexion,null);
        //UserLogin();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        View rootView = inflater.inflate(R.layout.fragment_recherche, container, false);
        VillDep_et = (EditText) rootView.findViewById(R.id.editTextVilleDep);
        VillArr_et = (EditText) rootView.findViewById(R.id.editTextVilleArr);

        Button btnRech = (Button) rootView.findViewById(R.id.btnRech);
        btnRech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (VillDep_et.getText().toString().length()== 0) {
                        CustomDialag("Saisir votre ville de départ");
                    } else if (VillArr_et.getText().toString().length() == 0) {
                        CustomDialag("Saisir votre ville d'arrivé");
                    }
                    else
                    {
                        Intent myIntent = new Intent(getActivity(), Liste_Recherche.class);
                        getActivity().startActivity(myIntent);
                    }

            }
        });

        TestVide(VillDep_et, getResources().getString(R.string.erreur_champvide));
        TestVide(VillArr_et, getResources().getString(R.string.erreur_champvide));
        return rootView;


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
        final Dialog dialog = new Dialog(getActivity());
        // Include dialog.xml file
        dialog.setContentView(R.layout.custom_dialog);
        // Set dialog title


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

    public void onBackPressed() {

        android.os.Process.killProcess(android.os.Process.myPid());

        // This above line close correctly
    }


}
