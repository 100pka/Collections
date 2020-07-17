package com.stopkaaaa.collections.ui.fragment.collections;

import android.os.Bundle;
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
import com.stopkaaaa.collections.model.CollectionSupplier;
import com.stopkaaaa.collections.ui.StartAmountView;
import com.stopkaaaa.collections.ui.fragment.recycler.CollectionsRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionsFragment extends Fragment implements CollectionsFragmentContract.View {

    private CollectionsFragmentContract.Presenter collectionsFragmentPresenter;

    @BindView(R.id.startAmountCollections)
    StartAmountView startAmountView;

    @BindView(R.id.collectionsRecycler)
    RecyclerView collectionsRecycler;

    private final CollectionsRecyclerAdapter collectionsRecyclerAdapter =
            new CollectionsRecyclerAdapter();

    public CollectionsFragment() {
        // Required empty public constructor
    }

    public static CollectionsFragment newInstance() {
        return new CollectionsFragment();
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
                if (isChecked) {
                    final CalculationParameters calculationParameters = startAmountView.getCalculationData();
                    collectionsFragmentPresenter.onStartButtonClicked(calculationParameters);
                }
            }
        });
        collectionsRecycler.setLayoutManager(new GridLayoutManager(getContext(), collectionsFragmentPresenter.getSpanCount()));
        collectionsRecycler.setAdapter(collectionsRecyclerAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collectionsFragmentPresenter =
                new CollectionsFragmentPresenter(this, CollectionSupplier.getInstance(getActivity().getApplicationContext()));
        collectionsFragmentPresenter.setup();
    }

    @Override
    public void setRecyclerAdapterData(ArrayList<CalculationResultItem> list) {
        collectionsRecyclerAdapter.setItems(list);
    }

    @Override
    public void uncheckStartButton() {
        startAmountView.uncheckStartButton();
    }

}
