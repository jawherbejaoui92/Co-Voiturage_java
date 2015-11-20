package com.androidbelieve.drawerwithswipetabs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Jawher B on 30/10/2015.
 */
public class Inscription_1_2 extends AppCompatActivity {

    String name, surname, pw, city, mail, telp;

    EditText email, mdp, nom, prenom, telephon, ville;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        email = (EditText) findViewById(R.id.editTextmail);
        mdp = (EditText) findViewById(R.id.editTextmdp);
        EditText confirmermdp = (EditText) findViewById(R.id.editTextconfirmer_mdp);
        nom = (EditText) findViewById(R.id.editTextnom);
        prenom = (EditText) findViewById(R.id.editTextprenom);
        telephon = (EditText) findViewById(R.id.editTexttelephone);
        ville = (EditText) findViewById(R.id.editTextville);
        EditText tel = (EditText) findViewById(R.id.editTexttelephone);
        DatePicker dateNaiss = (DatePicker) findViewById(R.id.datePickerNaiss);
        EditText rep = (EditText) findViewById(R.id.editText_Reponse);
        Spinner quest = (Spinner) findViewById(R.id.spinner_question);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.liste_question, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quest.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button button_inscrit = (Button) findViewById(R.id.buttonINSCRI);


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
        TestVide(rep, getResources().getString(R.string.erreur_champvide));
        TestLonguer(rep, getResources().getString(R.string.erreurmdp_inf));

        TestLonguerTel(tel, getResources().getString(R.string.erreur_tel));
        String strEmailAddress;


        button_inscrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TestAll() == "done") {


                    name = nom.getText().toString();
                    pw = mdp.getText().toString();
                    surname = prenom.getText().toString();
                    telp = telephon.getText().toString();
                    city = ville.getText().toString();
                    mail = email.getText().toString();
                    UserInsc();


                    Intent myIntent = new Intent(Inscription_1_2.this, MainActivity.class);
                    startActivity(myIntent);

                } else {
                    CustomDialag(TestAll());
                }

            }
        });
    }


    public String TestAll() {
        String msg;
        EditText email = (EditText) findViewById(R.id.editTextmail);
        EditText mdp = (EditText) findViewById(R.id.editTextmdp);
        EditText confirmermdp = (EditText) findViewById(R.id.editTextconfirmer_mdp);
        EditText nom = (EditText) findViewById(R.id.editTextnom);
        EditText prenom = (EditText) findViewById(R.id.editTextprenom);
        RadioGroup radioGroupsex = (RadioGroup) findViewById(R.id.RadioGroupsex);
        RadioButton homme = (RadioButton) findViewById(R.id.radioButtonhomme);
        RadioButton femme = (RadioButton) findViewById(R.id.radioButtonfemme);
        EditText tel = (EditText) findViewById(R.id.editTexttelephone);
        EditText ville = (EditText) findViewById(R.id.editTextville);
        DatePicker dateNaiss = (DatePicker) findViewById(R.id.datePickerNaiss);
        EditText rep = (EditText) findViewById(R.id.editText_Reponse);

        Spinner quest = (Spinner) findViewById(R.id.spinner_question);



        if ((email.getText().toString().length() < 4) || (mdp.getText().toString().length() < 4) || (confirmermdp.getText().toString().length() < 4) || (nom.getText().toString().length() < 4) || (prenom.getText().toString().length() < 4) || (tel.getText().toString().length() < 4) || (ville.getText().toString().length() < 4)) {
            msg = "4 caractères minimum pour chaque champ";
        } else if (!(mdp.getText().toString().equals(confirmermdp.getText().toString()))) {
            msg = "Mot de passe non identiques";
        } else if (!(homme.isChecked()) && !(femme.isChecked())) {
            msg = "Selectionne votre sexe";
        } else if ((tel.getText().toString().length() != 8)) {
            msg = "Numéro de téléphone invalide";
        } else if (2015 - dateNaiss.getYear() < 18) {
            msg = "Date de naissance invalide";
        } else if (quest.getSelectedItemPosition() == 0) {
            msg = "Choisir votre question de sécurité";
        } else if ((rep.getText().toString().length() < 4)) {
            msg = "4 caractères minimum pour votre réponse";
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

    public void TestMotdePasse(final EditText champ1, final EditText champ2, final String msg) {

        champ2.addTextChangedListener(new TextWatcher() {


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


    //ok
    //oui
    public void UserInsc() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("email", "benas@gmail.com"));
        nameValuePairs.add(new BasicNameValuePair("nom", "med bns"));
        nameValuePairs.add(new BasicNameValuePair("pw", "passssss"));
        nameValuePairs.add(new BasicNameValuePair("prenom", "thebns"));
        nameValuePairs.add(new BasicNameValuePair("telephone", "55908712"));
        nameValuePairs.add(new BasicNameValuePair("ville", "brdoCity"));

        try {
            /*JSONObject json = new JSONObject();
            json.put("mail", "belhassen.bens@gmail.com");
            json.put("password", "bens");*/
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
            HttpConnectionParams.setSoTimeout(httpParams, 5000);
            HttpClient client = new DefaultHttpClient(httpParams);
            String url = "http://192.168.1.1/co-voiturage_php/covWS/inscription.php";

            HttpPost request = new HttpPost(url);
            // request.setEntity(new ByteArrayEntity(json.toString().getBytes("UTF8")));
            //request.setHeader("json", json.toString());
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            if (entity != null) {
                InputStream instream = entity.getContent();
                String result = convertStreamToString(instream);

                JSONObject json_data = new JSONObject(result);
                int code = (json_data.getInt("code"));
                if (code == 1) {

                    Toast.makeText(Inscription_1_2.this, "Inscri OK",
                            Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(Inscription_1_2.this, MainActivity.class);
                    startActivity(myIntent);
                } else {
                    Toast.makeText(Inscription_1_2.this, "Sorry, Try Again",
                            Toast.LENGTH_LONG).show();
                }
            }
        } catch (Throwable t) {
            Toast.makeText(Inscription_1_2.this, "Request failed: " + t.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

    public static String convertStreamToString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, HTTP.UTF_8));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
        } catch (OutOfMemoryError om) {
            om.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
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


