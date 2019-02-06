package com.ravenshell.peerstream.utils.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

import com.ravenshell.peerstream.devicelist.DeviceListPresenter;

public class WifiP2pBroadcastReceiver extends BroadcastReceiver {


    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private DeviceListPresenter mPresenter;
    private WifiP2pManager.PeerListListener mPeerListListener;
    private WifiP2pDeviceList mDeviceList = null;


    public WifiP2pBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel wifiChannel, DeviceListPresenter presenter) {
        mManager = manager;
        mChannel = wifiChannel;
        mPresenter = presenter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)){

        }else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)){

        }else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)){

            mPeerListListener = new WifiP2pManager.PeerListListener() {
                @Override
                public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
                    mDeviceList = wifiP2pDeviceList;
                }
            };

            if (null != mManager){
                mManager.requestPeers(mChannel, mPeerListListener);
            }

        }else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)){

            if(intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1) == WifiP2pManager.WIFI_P2P_STATE_ENABLED){

            }else{

            }

        }

    }

    public WifiP2pDeviceList getDeviceList(){
        return mDeviceList;
    }


}
