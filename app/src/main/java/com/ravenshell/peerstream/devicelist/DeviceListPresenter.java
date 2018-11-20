package com.ravenshell.peerstream.devicelist;

import android.content.Context;
import android.net.wifi.ScanResult;

import com.ravenshell.peerstream.mvp.BasePresenter;
import com.ravenshell.peerstream.wificonnector.Device;
import com.ravenshell.peerstream.wificonnector.scanner.ScannerContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ogie on 11/19/2018.
 */

public class DeviceListPresenter implements DeviceListContract.Action, BasePresenter {


    private final DeviceListContract.View mView;
    private final ScannerContract mScanner;

    public DeviceListPresenter(Context ctx, DeviceListContract.View view, ScannerContract scanner) {
        mView = view;
        mScanner = scanner;
    }

    @Override
    public void beginSearch() {
        mView.displayLoader();
        mScanner.startScan(this);
    }

    @Override
    public void getDevices(List<ScanResult> devices) {
        mView.hideLoader();


        // convert ScanResult to Device before display
         mView.displayDevices(new ArrayList<Device>());
    }

    @Override
    public void refresh() {
        mView.displayLoader();
        mScanner.startScan(this);
    }

    // notify view to display loader
    @Override
    public void onViewAttached() {

    }

    @Override
    public void handleSearchError() {
        mView.displayMsg("Error Searching for new Devices");
    }
}
