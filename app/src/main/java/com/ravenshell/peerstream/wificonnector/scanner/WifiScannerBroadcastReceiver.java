package com.ravenshell.peerstream.wificonnector.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

/**
 * Created by ogie on 11/20/2018.
 */

public class WifiScannerBroadcastReceiver extends BroadcastReceiver {

    private final ScannerContract.Callback mScannerCallback;

    public WifiScannerBroadcastReceiver(ScannerContract.Callback callback) {
        mScannerCallback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Boolean successFlag = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
        if (successFlag) {
            mScannerCallback.onScannerSuccessful();
        } else {
            mScannerCallback.onError();
        }
    }

}
