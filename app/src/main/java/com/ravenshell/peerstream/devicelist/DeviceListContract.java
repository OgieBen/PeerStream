package com.ravenshell.peerstream.devicelist;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;

import java.util.List;

/**
 * Created by ogie on 11/19/2018.
 */

public interface DeviceListContract {

    interface View {
        void displayDevices(List<WifiP2pDevice> devices);
        void hideLoader();
        void displayLoader();
        void displayMsg(String str);
    }

    interface Action {
        void beginSearch();
        void refresh();
        void getDevices(WifiP2pDeviceList devices);
        void handleSearchError();
    }
}
