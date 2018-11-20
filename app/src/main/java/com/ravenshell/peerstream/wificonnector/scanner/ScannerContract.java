package com.ravenshell.peerstream.wificonnector.scanner;

/**
 * Created by ogie on 11/19/2018.
 */

public interface Scanner {

    interface Callback {
        void onScannerSuccessful();
        void onError();
    }
}
