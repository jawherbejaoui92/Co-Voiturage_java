package com.androidbelieve.drawerwithswipetabs;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Jawher B on 05/12/2015.
 */
public class Map_depart extends AppCompatActivity implements LocationListener {

    private GoogleMap map;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_depart);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER

        /*************************--map event--**************************************/
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            MarkerOptions markerOptions = new MarkerOptions();

            @Override
            public void onMapClick(LatLng latLng) {
                // Setting the position for the marker
                markerOptions.position(latLng);
                // Clears the previously touched position
                map.clear();
                // Animating to the touched position
                map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                // Placing a marker on the touched position
                map.addMarker(markerOptions);
                markerOptions.title("Position de départ");

                LatLng lL = markerOptions.getPosition();
                final double latitude = lL.latitude;
                final double longitude = lL.longitude;

                Geocoder geocoder = new Geocoder(Map_depart.this, Locale.getDefault());
                List<Address> addresses = null;
                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final String rue = addresses.get(0).getAddressLine(0);
                final String ville = addresses.get(0).getAddressLine(1);

                Toast.makeText(Map_depart.this,rue + ", " +ville, LENGTH_LONG).show();


                Button btn = (Button) findViewById(R.id.buttonSuivant);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(Map_depart.this, Map_Arrivee.class);
                        myIntent.putExtra("rue", rue);
                        myIntent.putExtra("ville", ville);
                        myIntent.putExtra("latitude", latitude);
                        myIntent.putExtra("longitude", longitude);
                        Map_depart.this.startActivity(myIntent);
                    }
                });

            }
        });
        /*************************************************************************/

    }







    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        map.addMarker(new MarkerOptions().position(latLng).title("votre position"));
        map.animateCamera(cameraUpdate);
        locationManager.removeUpdates(this);

        final double latitude = location.getLatitude();
        final double longitude = location.getLongitude();
        Geocoder geocoder = new Geocoder(Map_depart.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String rue = addresses.get(0).getAddressLine(0);
        final String ville = addresses.get(0).getAddressLine(1);

        Button btn = (Button) findViewById(R.id.buttonSuivant);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Map_depart.this, Map_Arrivee.class);
                myIntent.putExtra("rue", rue);
                myIntent.putExtra("ville", ville);
                myIntent.putExtra("latitude", latitude);
                myIntent.putExtra("longitude", longitude);
                Map_depart.this.startActivity(myIntent);
            }
        });


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }

    @Override
    public void onProviderEnabled(String provider) { }

    @Override
    public void onProviderDisabled(String provider) { }


    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;

    }
}