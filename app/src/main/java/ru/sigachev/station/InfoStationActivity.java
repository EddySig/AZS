package ru.sigachev.station;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InfoStationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_station);

        ViewPager pager=(ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new AdapterPage(getSupportFragmentManager()));
    }

    @Override
    public void onClick(View v) {

    }
}
