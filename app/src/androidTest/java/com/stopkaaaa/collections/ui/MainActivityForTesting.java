package com.stopkaaaa.collections.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.ui.fragment.mapcollection.MapCollectionFragmentTest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivityForTesting extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MapCollectionFragmentTest collectionsFragment = MapCollectionFragmentTest.newInstance(R.string.collections);
        MapCollectionFragmentTest mapsFragment = MapCollectionFragmentTest.newInstance(R.string.maps);

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