package com.stopkaaaa.collections.ui.fragment.maps;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stopkaaaa.collections.R;
import com.stopkaaaa.collections.dto.CalculationParameters;
import com.stopkaaaa.collections.dto.CalculationResultItem;
import com.stopkaaaa.collections.ui.fragment.recycler.CollectionsRecyclerAdapter;
import com.stopkaaaa.collections.ui.fragment.view.StartAmountView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MapsFragment extends Fragment implements MapsFragmentContract.View {

    private MapsFragmentContract.Presenter mapsFragmentPresenter;


    @BindView(R.id.startAmountCollections)
    StartAmountView startAmountView;

    @BindView(R.id.mapsRecycler)
    RecyclerView mapsRecycler;

    private final CollectionsRecyclerAdapter mapsRecyclerAdapter =
            new CollectionsRecyclerAdapter();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public MapsFragment() {
        // Required empty public constructor
    }

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_maps, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapsFragmentPresenter = MapFragmentInjector.getPresenter(
                this, getContext());
        mapsFragmentPresenter.setup();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startAmountView.setOnStartCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setChecked(isChecked);
                mapsRecyclerAdapter.showProgress(isChecked);
                if (isChecked) {
                    final CalculationParameters calculationParameters = startAmountView.getCalculationData();
                    mapsFragmentPresenter.onCalculationLaunch(calculationParameters);
                }
            }
        });
        mapsRecycler.setLayoutManager(new GridLayoutManager(getContext(), mapsFragmentPresenter.getSpanCount()));
        mapsRecycler.setAdapter(mapsRecyclerAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mapsRecyclerAdapter.getItemCount() == 0) {
            mapsFragmentPresenter.setup();
        }
    }

    @Override
    public void setRecyclerAdapterData(List<CalculationResultItem> list) {
        mapsRecyclerAdapter.setItems(list);
    }

    @Override
    public void updateItem(final int itemIndex, final String time) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                mapsRecyclerAdapter.updateItem(itemIndex, time);
            }
        });
    }

    @Override
    public void uncheckStartButton() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                startAmountView.uncheckStartButton();
            }
        });
    }

    @Override
    public void invalidMapSize() {
        startAmountView.invalidSizeNotification();
    }

    @Override
    public void invalidThreadsAmount() {
        startAmountView.invalidThreadsAmountNotification();
    }

}
