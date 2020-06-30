package com.stopkaaaa.collections;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.stopkaaaa.collections.ui.fragment.collections.CollectionsFragment;
import com.stopkaaaa.collections.ui.fragment.collections.CollectionsFragmentPresenter;
import com.stopkaaaa.collections.ui.fragment.maps.MapsFragment;
import com.stopkaaaa.collections.ui.fragment.maps.MapsFragmentPresenter;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tableLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    PageAdapter pageAdapter;

    private CollectionsFragmentPresenter collectionsFragmentPresenter;
    private MapsFragmentPresenter mapsFragmentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        CollectionsFragment collectionsFragment = CollectionsFragment.newInstance();
        collectionsFragmentPresenter = new CollectionsFragmentPresenter(collectionsFragment);
        collectionsFragment.setPresenter(collectionsFragmentPresenter);

        MapsFragment mapsFragment = MapsFragment.newInstance();
        mapsFragmentPresenter = new MapsFragmentPresenter(mapsFragment);
        mapsFragment.setPresenter(mapsFragmentPresenter);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(collectionsFragment, getString(R.string.collections));
        pageAdapter.addFragment(mapsFragment, getString(R.string.maps));

        viewPager.setAdapter(pageAdapter);
        tableLayout.setupWithViewPager(viewPager);
    }
}

