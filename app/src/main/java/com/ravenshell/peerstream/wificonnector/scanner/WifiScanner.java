package com.ravenshell.peerstream.wificonnector.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import com.ravenshell.peerstream.devicelist.DeviceListContract;
import com.ravenshell.peerstream.wificonnector.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ogie on 11/18/2018.
 */

public class WifiScanner implements ScannerContract, ScannerContract.Callback {

    private Context ctx;
    private WifiManager mWifiManager;
    private List<ScanResult> mDevices;
    private BroadcastReceiver mBroadcastReceiver;
    private IntentFilter mIntentFilter;
    private DeviceListContract.Action mPresenter;

    public WifiScanner(Context context, IntentFilter filter) {
        ctx = context;
        mIntentFilter = filter;
        mBroadcastReceiver = new WifiScannerBroadcastReceiver(this);
    }


    public void setupWifiManager() {
        mWifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        setupIntentFilter();
    }

    private void setupIntentFilter() {

        mIntentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        if (ctx != null) {
            ctx.registerReceiver(mBroadcastReceiver, mIntentFilter);
        }
    }

    void setPresnter(DeviceListContract.Action presenter) {
        mPresenter = presenter;
    }

    @Override
    public void startScan(DeviceListContract.Action presenter) {
        setPresnter(presenter);
        setupWifiManager();
    }

    @Override
    public void updateDeviceList() {
        mDevices = mWifiManager.getScanResults();
        if (mDevices == null) {
            mPresenter.handleSearchError();
            return;
        }
        mPresenter.getDevices(mDevices);
    }

    @Override
    public void handleSearchError() {
        mDevices = mWifiManager.getScanResults();
        if (mDevices == null) {
            mPresenter.handleSearchError();
            return;
        }

        mPresenter.handleSearchError();
        mPresenter.getDevices(mDevices);
    }

    @Override
    public void onScannerSuccessful() {
        this.updateDeviceList();
    }

    @Override
    public void onError() {
        this.handleSearchError();
    }

    @Override
    public List<ScanResult> getDevices() {
        return mDevices;
    }

    public List<Device> beginSearch() {

        return new ArrayList<>(0);
    }

    public void stopSearch() {

    }

}
