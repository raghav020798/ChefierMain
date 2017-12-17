package com.example.raghav.chefiermain.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.raghav.chefiermain.Fragments.TabFragment;
import com.example.raghav.chefiermain.Models.Orders;
import java.util.ArrayList;

/**
 * Created by RyanBansal on 12/17/17.
 */

public class MyFragmentAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Orders> ordersData = new ArrayList<>();


    public enum Tab {
        TODAYS , SPECIALITY, HANDCRAFTED;
    }

    final Tab[] tabs = Tab.values();
    final CharSequence[] titles = new CharSequence[tabs.length];

    public MyFragmentAdapter(FragmentManager fm, ArrayList<Orders> ordersArrayList) {
        super(fm);
        for (int i=0;i<3;i++) {
            titles[i] = tabs[i].toString();
        }
        ordersData = ordersArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        TabFragment fragment = new TabFragment();
        if (position == 0)
            fragment.setData(ordersData);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
