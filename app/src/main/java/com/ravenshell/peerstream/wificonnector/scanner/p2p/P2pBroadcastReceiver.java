package com.ravenshell.peerstream.wificonnector.scanner.p2p;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;

/**
 * Created by ogie on 11/20/2018.
 */

public class P2pBroadcastReceiver extends BroadcastReceiver {

    private final Activity mActivity;
    private final WifiP2pManager mWifiP2pManager;
    private final WifiP2pManager.Channel mChannel;

    public P2pBroadcastReceiver(Activity activity, WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel) {
        mActivity = activity;
        mWifiP2pManager = wifiP2pManager;
        mChannel = channel;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION:
                int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
//                    mActivity.setIsWifiP2pEnabled(true);
                } else {
//                    mActivity.setIsWifiP2pEnabled(false);
                }
                break;
            case WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION:
                break;
            case WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION:
                break;
            case WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION:
                break;

            default: ;
        }
    }
}
