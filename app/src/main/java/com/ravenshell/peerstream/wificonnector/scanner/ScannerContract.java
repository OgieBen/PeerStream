package com.ravenshell.peerstream.wificonnector.scanner;

import android.net.wifi.ScanResult;

import com.ravenshell.peerstream.devicelist.DeviceListContract;

import java.util.List;

/**
 * Created by ogie on 11/19/2018.
 */

public interface ScannerContract {


    void updateDeviceList();
    void handleSearchError();
    List<ScanResult> getDevices();
    void startScan(DeviceListContract.Action presenter);

    interface Callback {
        void onScannerSuccessful();
        void onError();
    }
}
