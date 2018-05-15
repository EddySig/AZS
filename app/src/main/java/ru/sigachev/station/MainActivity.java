package ru.sigachev.station;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private TextView btnLC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager=(ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new AdapterPage(getSupportFragmentManager()));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        btnLC = (TextView) findViewById(R.id.usrName);
        btnLC.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney and move the camera
        LatLng perm = new LatLng(58.0201783, 55.9540982);
        googleMap.addMarker(new MarkerOptions().position(perm).title("Marker in Perm"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(perm));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, LCActivity.class);
        startActivity(intent);
    }
}
