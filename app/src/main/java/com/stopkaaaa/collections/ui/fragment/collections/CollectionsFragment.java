package com.stopkaaaa.collections.ui.fragment.collections;

import android.content.Context;
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

import com.stopkaaaa.collections.model.CalculationParameters;
import com.stopkaaaa.collections.model.CalculationResult;
import com.stopkaaaa.collections.ui.StartAmountView;
import com.stopkaaaa.collections.ui.recycler.CollectionsRecyclerAdapter;

import com.stopkaaaa.collections.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollectionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollectionsFragment extends Fragment implements CollectionsFragmentContract.View {

    private CollectionsFragmentContract.Presenter mPresenter;

    @BindView(R.id.startAmountCollections)
    StartAmountView startAmountView;

    @BindView(R.id.collectionsRecycler)
    RecyclerView collectionsRecycler;

    private final CollectionsRecyclerAdapter collectionsRecyclerAdapter = new CollectionsRecyclerAdapter();

    public CollectionsFragment() {
        // Required empty public constructor
    }

    public static CollectionsFragment newInstance() { return new CollectionsFragment(); }



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
//        amountFragment = (StartAmountFragment) getChildFragmentManager().findFragmentById(R.id.startAmountCollections);
//        amountFragment.addOnNewCalculationDateListener(this);
        collectionsRecyclerAdapter.setItems(mPresenter.getRecyclerData());
        collectionsRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        collectionsRecycler.setAdapter(collectionsRecyclerAdapter);

        startAmountView.setOnStartButtonClickListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setChecked(isChecked);
                if (isChecked) {
                    final CalculationParameters calculationParameters = startAmountView.getCalculationData();
                    mPresenter.onStartButtonClicked(calculationParameters);
                }
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CollectionsFragmentPresenter(this);
    }

    @Override
    public void notifyRecyclerAdapter() {
        collectionsRecyclerAdapter.setItems(mPresenter.getRecyclerData());
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void uncheckStartButton() {
        startAmountView.uncheckStartButton();
    }

    //    public static List<String> getList() {
//        List<String> nameList = new ArrayList<String>();
//        nameList.add("Adding to start in ArrayList");
//        nameList.add("Adding to start in LinkedList");
//        nameList.add("Adding to start in CopyOnWriteList");
//
//        nameList.add("Adding to middle in ArrayList");
//        nameList.add("Adding to middle in LinkedList");
//        nameList.add("Adding to middle in CopyOnWriteList");
//
//        nameList.add("Adding to end in ArrayList");
//        nameList.add("Adding to end in LinkedList");
//        nameList.add("Adding to end in CopyOnWriteList");
//
//        nameList.add("Search in ArrayList");
//        nameList.add("Search in LinkedList");
//        nameList.add("Search in CopyOnWriteList");
//
//        nameList.add("Remove from start in ArrayList");
//        nameList.add("Remove from start in LinkedList");
//        nameList.add("Remove from start in CopyOnWriteList");
//
//        nameList.add("Remove from middle in ArrayList");
//        nameList.add("Remove from middle in LinkedList");
//        nameList.add("Remove from middle in CopyOnWriteList");
//
//        nameList.add("Remove from end in ArrayList");
//        nameList.add("Remove from end in LinkedList");
//        nameList.add("Remove from end in CopyOnWriteList");
//
//        return nameList;
//    }

//    @Override
//    public void onNewCalculationData(CalculationData calculationData) {
//        Log.d("LOGG", "Clicked " + calculationData);
//    }
//
//    @Override
//    public void onDestroyView() {
//        amountFragment.removeOnNewCalculationDateListener(this);
//        super.onDestroyView();
//    }


}
