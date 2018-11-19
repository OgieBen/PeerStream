package com.ravenshell.peerstream.devicelist;

import com.ravenshell.peerstream.wificonnector.Device;

import java.util.List;

/**
 * Created by ogie on 11/19/2018.
 */

public interface DeviceListContract {

    interface View {
        void displayView(View view);
        void hideView(View view);
        void displayDevices(List<Device> devices);
    }

    interface Action {
        void beginSearch();
        void refresh();
    }
}
