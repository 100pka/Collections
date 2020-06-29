package com.stopkaaaa.collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.stopkaaaa.collections.ui.fragment.collections.CollectionsFragment;
import com.stopkaaaa.collections.ui.fragment.collections.CollectionsFragmentPresenter;
import com.stopkaaaa.collections.ui.fragment.maps.MapsFragment;
import com.stopkaaaa.collections.ui.fragment.maps.MapsFragmentPresenter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    private static final int PAGE_NUMBER = 2;
    private CollectionsFragmentPresenter collectionsFragmentPresenter;
    private MapsFragmentPresenter mapsFragmentPresenter;

    public PageAdapter(FragmentManager fmng)
    {
        super(fmng);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:{
                CollectionsFragment collectionsFragment = CollectionsFragment.newInstance();
                collectionsFragmentPresenter = new CollectionsFragmentPresenter(collectionsFragment);
                return collectionsFragment;
            }
            case 1:{
                MapsFragment mapsFragment = MapsFragment.newInstance();
                mapsFragmentPresenter = new MapsFragmentPresenter(mapsFragment);
                return mapsFragment;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Collections";
            case 1:
                return "Maps";
            default:
                return "";
        }
    }
}
