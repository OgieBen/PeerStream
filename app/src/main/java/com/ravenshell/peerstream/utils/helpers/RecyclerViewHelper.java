package com.ravenshell.peerstream.utils.helpers;

import android.net.wifi.p2p.WifiP2pDevice;
import android.view.View;
import android.widget.TextView;

import com.ravenshell.peerstream.R;

public class RecyclerViewHelper {

    public static void updateDeviceListView(View view, WifiP2pDevice device) {

        TextView deviceNameTextView = view.findViewById(R.id.device_name);
        deviceNameTextView.setText(device.deviceName.toString());
    }
}
