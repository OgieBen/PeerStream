package com.ravenshell.peerstream.devicelist;

/**
 * Created by ogie on 11/19/2018.
 */

public class DeviceListPresenter implements DeviceListContract.Action{


    private final DeviceListContract.View mView;

    public DeviceListPresenter(DeviceListContract.View view) {
        mView = view;
    }

    @Override
    public void beginSearch() {

    }

    @Override
    public void refresh() {

    }
}
