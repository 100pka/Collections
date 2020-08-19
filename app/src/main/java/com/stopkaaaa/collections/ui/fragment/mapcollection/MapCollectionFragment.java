package com.stopkaaaa.collections.ui.fragment.mapcollection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.base.BaseContract;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.ui.fragment.FragmentInjector;
import com.stopkaaaa.collections.ui.fragment.recycler.CollectionsRecyclerAdapter;
import com.stopkaaaa.collections.ui.fragment.view.StartAmountView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapCollectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapCollectionFragment extends Fragment implements BaseContract.BaseView {


    private static final String PAGE = "PAGE";
    private BaseContract.BasePresenter collectionsFragmentPresenter;

    @BindView(R.id.startAmountCollections)
    StartAmountView startAmountView;

    @BindView(R.id.collectionsRecycler)
    RecyclerView collectionsRecycler;

    private final CollectionsRecyclerAdapter collectionsRecyclerAdapter =
            new CollectionsRecyclerAdapter();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public MapCollectionFragment() {
        // Required empty public constructor
    }

    public static MapCollectionFragment newInstance(@StringRes int page) {
        final Bundle args = new Bundle();
        args.putInt(PAGE, page);
        final MapCollectionFragment mapCollectionFragment = new MapCollectionFragment();
        mapCollectionFragment.setArguments(args);
        return mapCollectionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_collections, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startAmountView.setOnStartCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setChecked(isChecked);
                    final CalculationParameters calculationParameters = startAmountView.getCalculationData();
                    collectionsFragmentPresenter.onCalculationLaunch(calculationParameters);
            }
        });
        collectionsRecycler.setLayoutManager(new GridLayoutManager(getContext(), collectionsFragmentPresenter.getSpanCount()));
        collectionsRecycler.setAdapter(collectionsRecyclerAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectionsFragmentPresenter = FragmentInjector.getPresenter(
                this, getContext(), this.getArguments().getInt(PAGE));
    }

    @Override
    public void onStart() {
        super.onStart();
        if (collectionsRecyclerAdapter.getItemCount() == 0) {
            collectionsFragmentPresenter.setup();
        }
    }

    @Override
    public void setData(List<CalculationResultItem> list) {
        collectionsRecyclerAdapter.setItems(list);
    }

    @Override
    public void updateItem(final int itemIndex, final String time) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                collectionsRecyclerAdapter.updateItem(itemIndex, time);
            }
        });
    }

    @Override
    public void uncheckStartButton() {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                startAmountView.uncheckStartButton();
//            }
//        });
        startAmountView.uncheckStartButton();
    }

    @Override
    public void invalidCollectionSize() {
        startAmountView.invalidSizeNotification();
    }

    @Override
    public void invalidThreadsAmount() {
        startAmountView.invalidThreadsAmountNotification();
    }

    @Override
    public void showProgressBar(boolean calculationInProgress) {
        collectionsRecyclerAdapter.showProgress(calculationInProgress);
    }
}
