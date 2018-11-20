package com.ravenshell.peerstream.wificonnector;

import android.content.Context;

import com.ravenshell.peerstream.wificonnector.scanner.Scanner;
import com.ravenshell.peerstream.wificonnector.scanner.WifiScanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

/**
 * Created by ogie on 11/19/2018.
 */
public class WifiScannerTest {

//    @Mock
    Context context;
    private WifiScanner mWifiScanner;

    @Captor
    Scanner.Callback mScannerCallback;

    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
         mWifiScanner = new WifiScanner(context);

        //wifiScanner.setupWifiManager();
    }

    @Test
    public void beginSearch() throws Exception {

        mWifiScanner.setupWifiManager(mScannerCallback);



    }

    @Test
    public void stopSearch() throws Exception {
    }

}