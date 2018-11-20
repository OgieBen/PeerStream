package com.ravenshell.peerstream.wificonnector;

import android.content.Context;

import com.ravenshell.peerstream.wificonnector.scanner.WifiScanner;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by ogie on 11/19/2018.
 */
public class WifiScannerTest {

//    @Mock
    Context context;

    @Before
    public void setUp() throws Exception {

        WifiScanner wifiScanner = new WifiScanner(context);

        //wifiScanner.setupWifiManager();
    }

    @Test
    public void beginSearch() throws Exception {
    }

    @Test
    public void stopSearch() throws Exception {
    }

}