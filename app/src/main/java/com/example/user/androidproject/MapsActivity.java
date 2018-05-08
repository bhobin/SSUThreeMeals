package com.example.user.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();
        Double latitude =  intent.getDoubleExtra("LATITUDE",0);
        Double longitude = intent.getDoubleExtra("LONGITUDE",0);

        // Add a marker in Sydney and move the camera
        LatLng destination = new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(destination));
        CameraPosition ca = new CameraPosition(destination,19,0,0);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(ca));
    }
}