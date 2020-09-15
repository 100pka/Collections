package com.stopkaaaa.collections.ui.fragment.mapcollection;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stopkaaaa.collections.InitApplication;
import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.di.component.DaggerActivityComponentTest;
import com.stopkaaaa.collections.di.module.FragmentInjectorModuleTest;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.ui.fragment.recycler.CollectionsRecyclerAdapter;
import com.stopkaaaa.collections.ui.fragment.view.StartAmountView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapCollectionFragmentTest extends MapCollectionFragment {


    private static final String PAGE = "PAGE";


    public MapCollectionFragmentTest() {
        // Required empty public constructor
    }

    public static MapCollectionFragmentTest newInstance(@StringRes int page) {
        final Bundle args = new Bundle();
        args.putInt(PAGE, page);
        final MapCollectionFragmentTest mapCollectionFragment = new MapCollectionFragmentTest();
        mapCollectionFragment.setArguments(args);
        return mapCollectionFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerActivityComponentTest.builder()
                .appComponent(InitApplication.getInstance().component())
                .fragmentInjectorModuleTest(new FragmentInjectorModuleTest(this, this.getArguments().getInt(PAGE)))
                .build()
                .inject(this);
    }
}