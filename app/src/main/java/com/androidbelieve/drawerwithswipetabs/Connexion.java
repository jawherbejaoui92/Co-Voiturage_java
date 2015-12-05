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
 * Created by dfghj on 04/11/2015.
 */

public class  Connexion extends Activity {
    InputStream is;
    String result,name,line,mail,pass;
    EditText email_et,pw_et;
    Button btn_cnx,btn_ins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        email_et = (EditText)findViewById(R.id.editTextmail);
        pw_et = (EditText)findViewById(R.id.editTextmdp);

        TestVide(email_et, getResources().getString(R.string.erreur_champvide));
        TestVide(pw_et, getResources().getString(R.string.erreur_champvide));
        TestLonguer(email_et, getResources().getString(R.string.erreurmdp_inf));
        TestLonguer(pw_et, getResources().getString(R.string.erreurmdp_inf));

        btn_ins = (Button)findViewById(R.id.buttoninscription);


        btn_ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Connexion.this, Inscription.class);
                Connexion.this.startActivity(myIntent);
            }
        });


        btn_cnx = (Button)findViewById(R.id.buttonconnexion);
        btn_cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TestAll() == "done") {
                    mail = email_et.getText().toString();
                    pass = pw_et.getText().toString();
                    UserLogin();
                }
                else {
                    CustomDialag(TestAll());
                }

            }
        });

    }


    public void UserLogin(){
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("mail", mail));
        nameValuePairs.add(new BasicNameValuePair("pw", pass));
        try {
            /*JSONObject json = new JSONObject();
            json.put("mail", "belhassen.bens@gmail.com");
            json.put("password", "bens");*/
            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
            HttpConnectionParams.setSoTimeout(httpParams, 5000);
            HttpClient client = new DefaultHttpClient(httpParams);
            String url = "http://192.168.56.1/co-voiturage_php/covWS/Login.php";

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

                    Toast.makeText(Connexion.this, "Connexion OK",
                            Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(Connexion.this, MainActivity.class);
                    Connexion.this.startActivity(myIntent);
                }
                else
                {
                    Toast.makeText(Connexion.this, "Sorry, Try Again",
                            Toast.LENGTH_LONG).show();
                }
            }
        } catch (Throwable t) {
            Toast.makeText(Connexion.this, "Request failed: " + t.toString(),
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

    public String TestAll() {
        String msg;
        EditText email = (EditText)findViewById(R.id.editTextmail);
        EditText mdp = (EditText)findViewById(R.id.editTextmdp);


        if ((email.getText().toString().length() < 4) || (mdp.getText().toString().length() < 4)) {
            msg = "4 caractères minimum pour chaque champ";
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

    public void CustomDialag(String msg) {
        // Create custom dialog object
        final Dialog dialog = new Dialog(Connexion.this);
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

    public void onBackPressed() {

        android.os.Process.killProcess(android.os.Process.myPid());

        // This above line close correctly
    }


}
