package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    String name,surname,pw,city,mail,telp;

    EditText email,mdp,nom,prenom,telephon,ville;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_1_2);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final Tester_champ test = new Tester_champ();

        email = (EditText) findViewById(R.id.editTextmail);
        mdp = (EditText) findViewById(R.id.editTextmdp);
        EditText confirmermdp = (EditText) findViewById(R.id.editTextconfirmer_mdp);
        nom = (EditText) findViewById(R.id.editTextnom);
        prenom = (EditText) findViewById(R.id.editTextprenom);
        RadioGroup radioGroupsex = (RadioGroup) findViewById(R.id.RadioGroupsex);
        RadioButton homme = (RadioButton) findViewById(R.id.radioButtonhomme);
        RadioButton femme = (RadioButton) findViewById(R.id.radioButtonfemme);
        telephon = (EditText) findViewById(R.id.editTexttelephone);
        ville = (EditText) findViewById(R.id.editTextville);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button button_suivant = (Button) findViewById(R.id.buttonsuivant);



        test.TestVide(email, getResources().getString(R.string.erreur_champvide));
        test.TestVide(mdp, getResources().getString(R.string.erreur_champvide));
        test.TestVide(confirmermdp, getResources().getString(R.string.erreur_champvide));
        test.TestVide(nom, getResources().getString(R.string.erreur_champvide));
        test.TestVide(prenom, getResources().getString(R.string.erreur_champvide));
        test.TestVide(telephon, getResources().getString(R.string.erreur_champvide));
        test.TestVide(ville, getResources().getString(R.string.erreur_champvide));

        test.TestMotdePasse(mdp, confirmermdp, getResources().getString(R.string.erreurmdp_identique), getResources().getString(R.string.erreur_inf));

        test.TestLonguer(email, getResources().getString(R.string.erreurmdp_inf));
        test.TestLonguer(nom, getResources().getString(R.string.erreurmdp_inf));
        test.TestLonguer(prenom,getResources().getString(R.string.erreurmdp_inf));
        test.TestLonguer(mdp,getResources().getString(R.string.erreurmdp_inf));
        test.TestLonguer(confirmermdp,getResources().getString(R.string.erreurmdp_inf));
        test.TestLonguer(ville,getResources().getString(R.string.erreurmdp_inf));

        test.TestLonguerTel(telephon, getResources().getString(R.string.erreur_tel));

        button_suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nom.getText().toString();
                pw = mdp.getText().toString();
                surname = prenom.getText().toString();
                telp = telephon.getText().toString();
                city = ville.getText().toString();
                mail = email.getText().toString();
                UserInsc();
                //CustomDialag();

            }
        });
    }
//ok
    public void UserInsc(){
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
            String url = "http://127.0.0.1/co-voiturage_php/covWS/inscription.php";

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
                int code=(json_data.getInt("code"));
                if(code==1)
                {

                    Toast.makeText(Inscription_1_2.this, "Inscri OK",
                            Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(Inscription_1_2.this, Inscription_2_2.class);
                    startActivity(myIntent);
                }
                else
                {
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
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, HTTP.UTF_8));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
        } catch(OutOfMemoryError om){
            om.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return sb.toString();
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


