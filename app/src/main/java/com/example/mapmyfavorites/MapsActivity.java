package com.example.mapmyfavorites;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;

import java.util.Iterator;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button seeList = findViewById(R.id.seeList);
        seeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListPlaces.class);
                //EditText editText = (EditText) findViewById(R.id.editText);
                startActivity(intent);


            }
        });


    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        final GoogleMap.OnInfoWindowClickListener listener = this;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("New Location"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.0f));
                mMap.setOnInfoWindowClickListener(listener);
            }
        });

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        // open the form activity
        Intent intent = new Intent(this, MarkerFormActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        System.out.println(marker.getPosition().latitude);
        intent.putExtra("latitude", "" + marker.getPosition().latitude);
        intent.putExtra("longitude", "" + marker.getPosition().longitude);
        startActivity(intent);


    }

}
