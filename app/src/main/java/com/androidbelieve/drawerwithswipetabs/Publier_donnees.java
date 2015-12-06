package com.androidbelieve.drawerwithswipetabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Jawher B on 05/12/2015.
 */
public class Publier_donnees extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publier_donnees);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Détails");

        /********************************View***********************************/
        TextView v_depart = (TextView)findViewById(R.id.textView_villedepart);
        TextView v_arrivee = (TextView)findViewById(R.id.textView_arrivee);
        TextView txtv_distance = (TextView)findViewById(R.id.textViewDistance);
        TimePicker heure = (TimePicker) findViewById(R.id.timePickerDepart);
        heure.setIs24HourView(true);

        EditText tel = (EditText)findViewById(R.id.editTex_tel);
        NumberPicker nbre_place = (NumberPicker) findViewById(R.id.nbre_place);
        nbre_place.setMinValue(1);
        nbre_place.setMaxValue(8);

        EditText tarif = (EditText)findViewById(R.id.editText_tarif);

        RadioButton fumeOui = (RadioButton)findViewById(R.id.fumeOui);
        RadioButton fumeNon = (RadioButton)findViewById(R.id.fumeNon);

        RadioButton animauxOui = (RadioButton)findViewById(R.id.animauxOui);
        RadioButton animauxNon = (RadioButton)findViewById(R.id.animauxNon);

        RadioButton bagageOui = (RadioButton)findViewById(R.id.bagageOui);
        RadioButton bagageNon = (RadioButton)findViewById(R.id.bagageNon);

        RadioButton activeOui = (RadioButton)findViewById(R.id.activeOui);
        RadioButton activeNon = (RadioButton)findViewById(R.id.activeNon);
        /***********************************************************************/



        Intent myIntent= getIntent();
        String ruedepart = myIntent.getStringExtra("ruedepart");
        String villedepart = myIntent.getStringExtra("villedepart");
        double latitudedepart = myIntent.getDoubleExtra("latitudedepart", 0);
        double longitudedepart = myIntent.getDoubleExtra("longitudedepart", 0);

        String ruearrivee = myIntent.getStringExtra("ruearrivee");
        String villearrivee = myIntent.getStringExtra("villearrivee");
        double latitudearrivee = myIntent.getDoubleExtra("latitudearrivee", 0);
        double longitudearrivee = myIntent.getDoubleExtra("longitudearrivee", 0);

        /********************************************************************/
        String distance = getDistance(latitudedepart,longitudedepart,latitudearrivee,longitudearrivee);


        String point_depart = ruedepart+", "+villedepart;
        String point_arrivee= ruearrivee+", "+villearrivee;

        v_depart.setText(point_depart);
        v_arrivee.setText(point_arrivee);
        txtv_distance.setText(distance);

        /********************************************************************/

    }

    public String getDistance(double lat1, double lon1, double lat2, double lon2) {
        String distance = "";
        System.out.println(lat1+" "+lon1+" "+lat2+" "+lon2);
        String url = "http://maps.google.com/maps/api/directions/xml?origin=" + lat1 + "," + lon1 + "&destination=" + lat2 + "," + lon2 + "&sensor=false&units=metric";
        String tag[] = {"text"};  //will give distance as string e.g 1.2 km
// or tag[] = {"value"} if you want to get distance in metre e.g. 1234

        HttpResponse response = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(url);
            response = httpClient.execute(httpPost, localContext);
            InputStream is = response.getEntity().getContent();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(is);
            if (doc != null) {
                NodeList nl;
                ArrayList args = new ArrayList();
                for (String s : tag) {
                    nl = doc.getElementsByTagName(s);
                    if (nl.getLength() > 0) {
                        Node node = nl.item(nl.getLength() - 1);
                        args.add(node.getTextContent());
                    } else {
                        args.add(" - ");
                    }
                }
                distance = String.format("%s", args.get(0));
            }
            else
            {
                System.out.print("Doc is null");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return distance;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;

    }
}
