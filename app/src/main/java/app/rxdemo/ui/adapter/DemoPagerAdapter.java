package app.rxdemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import app.rxdemo.ui.fragments.StoreFragment;
import app.rxdemo.ui.fragments.BeerFragment;
import app.rxdemo.ui.fragments.ProductFragment;

/**
 * Created by atempa on 26/07/16.
 */
public class DemoPagerAdapter extends FragmentStatePagerAdapter {
    private final int NUM_TABS = 3;

    public DemoPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = BeerFragment.newInstance();
                break;
            case 1:
                fragment = ProductFragment.newInstance();
                break;
            case 2:
                fragment = StoreFragment.newInstance();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }
}
