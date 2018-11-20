package com.ravenshell.peerstream.devicelist;

import android.net.wifi.ScanResult;

import com.ravenshell.peerstream.wificonnector.Device;

import java.util.List;

/**
 * Created by ogie on 11/19/2018.
 */

public interface DeviceListContract {

    interface View {
        void displayDevices(List<Device> devices);
        void hideLoader();
        void displayLoader();
        void displayMsg(String str);
    }

    interface Action {
        void beginSearch();
        void refresh();
        void getDevices(List<ScanResult> devices);
        void handleSearchError();
    }
}
