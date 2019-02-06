package com.ravenshell.peerstream.devicelist;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;

import com.ravenshell.peerstream.mvp.BasePresenter;
import com.ravenshell.peerstream.wificonnector.scanner.ScannerContract;

import java.util.ArrayList;

/**
 * Created by ogie on 11/19/2018.
 */

public class DeviceListPresenter implements DeviceListContract.Action, BasePresenter {


    private final DeviceListContract.View mView;
    private final ScannerContract mScanner;
    private ArrayList<WifiP2pDevice> mDevices;

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
    public void getDevices(WifiP2pDeviceList devices) {
        mView.hideLoader();

        mDevices = new ArrayList<WifiP2pDevice>();
        for(WifiP2pDevice device: devices.getDeviceList()){
            mDevices.add(device);
        }

         mView.displayDevices(mDevices);
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
