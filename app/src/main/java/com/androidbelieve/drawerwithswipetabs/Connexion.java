package com.androidbelieve.drawerwithswipetabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by Ratan on 7/29/2015.
 */
public class  Connexion extends Activity {
    InputStream is;
    String result,name,line,mail,pass;
    EditText email_et,pw_et;
    Button btn_cnx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        email_et = (EditText)findViewById(R.id.editTextmail);
        pw_et = (EditText)findViewById(R.id.editTextmdp);

        btn_cnx = (Button)findViewById(R.id.buttonconnexion);
        btn_cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail= email_et.getText().toString();
                pass= pw_et.getText().toString();
                UserLogin();

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
            String url = "http://192.168.1.7/co-voiturage_php/covWS/Login.php";

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


}
