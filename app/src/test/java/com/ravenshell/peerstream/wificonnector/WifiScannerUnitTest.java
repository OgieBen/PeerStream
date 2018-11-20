package com.ravenshell.peerstream.wificonnector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

import com.ravenshell.peerstream.wificonnector.scanner.ScannerContract;
import com.ravenshell.peerstream.wificonnector.scanner.WifiScanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by ogie on 11/20/2018.
 */

public class WifiScannerUnitTest {

    @Mock
    Context context;

    @Mock
    IntentFilter mIntentFilter;

    @Mock
    BroadcastReceiver mBroadcastReceiver;

    private WifiScanner mWifiScanner;

    @Captor
    ArgumentCaptor<ScannerContract.Callback> mScannerCallback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mWifiScanner = new WifiScanner(context, mIntentFilter);
    }

    @Test
    public void beginSearch() throws Exception {

        mWifiScanner.setupWifiManager();
        verify(context).registerReceiver(mBroadcastReceiver, mIntentFilter);

//        List<ScanResult> result =  mWifiScanner.getDevices();
//
//        assertNotNull("Scan Result cannot be null", result);
//        assertSame(new ArrayList<ScanResult> (), result);
    }

    @Test
    public void stopSearch() throws Exception {
    }

}
