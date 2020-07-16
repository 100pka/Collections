package com.stopkaaaa.collections.ui.fragment.maps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.stopkaaaa.collections.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MapsFragment extends Fragment implements MapsFragmentContract.View {

    private MapsFragmentContract.Presenter mPresenter;


//    @BindView(R.id.mapsRecycler)
//    RecyclerView mapsRecycler;
//
//    RecyclerAdapter recyclerAdapter;

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
//        ButterKnife.bind(this, view);
//        mapsRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        recyclerAdapter = new RecyclerAdapter(getContext(), getList(), getList());
//        mapsRecycler.setAdapter(recyclerAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MapsFragmentPresenter(this);
    }


//    public static List<String> getList() {
//        List<String> nameList = new ArrayList<String>();
//        nameList.add("Adding to HashMap");
//        nameList.add("Adding to TreeMap");
//
//        nameList.add("Search in HashMap");
//        nameList.add("Search in TreeMap");
//
//        nameList.add("Removing from HashMap");
//        nameList.add("Removing from TreeMap");
//
//        return nameList;
//    }
}
