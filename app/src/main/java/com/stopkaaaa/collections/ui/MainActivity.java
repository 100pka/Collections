package com.stopkaaaa.collections.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.ui.fragment.mapcollection.MapCollectionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tableLayout;

    @BindView(R.id.viewPager)
    ViewPager2 viewPager;

    PageAdapter pageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MapCollectionFragment collectionsFragment = MapCollectionFragment.newInstance(R.string.collections);
        MapCollectionFragment mapsFragment =  MapCollectionFragment.newInstance(R.string.maps);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), getLifecycle());
        pageAdapter.addFragment(collectionsFragment, getString(R.string.collections));
        pageAdapter.addFragment(mapsFragment, getString(R.string.maps));

        viewPager.setAdapter(pageAdapter);
        new TabLayoutMediator(tableLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(pageAdapter.getTitles().get(position));
                    }
                }).attach();
    }
}

