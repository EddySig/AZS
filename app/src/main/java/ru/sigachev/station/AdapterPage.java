package ru.sigachev.station;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdapterPage extends FragmentPagerAdapter{

    public AdapterPage(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public Fragment getItem(int position) {
        return(PricePage.newInstance(position));
    }

    @Override
    public int getCount() {
        return 3;
    }
}
