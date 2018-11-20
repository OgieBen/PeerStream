package com.ravenshell.peerstream.wificonnector.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import com.ravenshell.peerstream.wificonnector.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ogie on 11/18/2018.
 */

public class WifiScanner {

    private Context ctx;
    private WifiManager mWifiManager;
    private List<ScanResult> mDevices;
    private BroadcastReceiver mBroadcastReceiver;

    public WifiScanner(Context context) {
        ctx = context;
    }


    public void setupWifiManager(final Scanner.Callback scanner){
        mWifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Boolean successFlag = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);

                if (successFlag){
                    updateDeviceList();
                    scanner.onScannerSuccessful();
                }else{
                    handleSearchFailure();
                    scanner.onError();
                }
            }
        };

        setupIntentFilter();
    }

    private void setupIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        if (ctx != null){
            ctx.registerReceiver(mBroadcastReceiver, filter);
        }
    }

    private void handleSearchFailure() {
        mDevices = mWifiManager.getScanResults();
    }

    private void updateDeviceList() {
        mDevices = mWifiManager.getScanResults();
    }



    public List<ScanResult> getDevices(){
        return mDevices;
    }

    public List<Device> beginSearch() {

        return new ArrayList<>(0);
    }

    public void stopSearch(){

    }

}
