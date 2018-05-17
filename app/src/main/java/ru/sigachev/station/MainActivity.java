package ru.sigachev.station;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
    private Dialog dialog;
    private TextView btnti;

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

        btnti = (TextView) findViewById(R.id.ti);
        btnti.setOnClickListener(this);

        dialog = new Dialog(MainActivity.this);
        // Передайте ссылку на разметку
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_load);

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
        switch (v.getId()){
            case R.id.usrName:
                dialog.show();
                Intent intent = new Intent(this, LCActivity.class);
                startActivity(intent);
                break;
            case R.id.ti:
                dialog.show();
                Intent intent1 = new Intent(this, InfoStationActivity.class);
                startActivity(intent1);
                break;
        }

    }
}
