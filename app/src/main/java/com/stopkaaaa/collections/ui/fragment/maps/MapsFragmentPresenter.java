package com.stopkaaaa.collections.ui.fragment.maps;

public class MapsFragmentPresenter implements MapsFragmentContract.Presenter {
    private final MapsFragmentContract.View mMapsFragmentContractView;


    public MapsFragmentPresenter(MapsFragmentContract.View mMapsFragmentContractView) {
        this.mMapsFragmentContractView = mMapsFragmentContractView;
    }

}
