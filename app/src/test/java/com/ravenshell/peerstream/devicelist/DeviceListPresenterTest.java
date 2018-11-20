package com.ravenshell.peerstream.devicelist;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;

import com.ravenshell.peerstream.wificonnector.Device;
import com.ravenshell.peerstream.wificonnector.scanner.ScannerContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by ogie on 11/20/2018.
 */
public class DeviceListPresenterTest {

    @Mock
    Context mContext;

    @Mock
    IntentFilter mIntentFilter;

    @Mock
    DeviceListContract.View  mView;

    @Mock
    ScannerContract mScannerContract;

    private DeviceListPresenter mDeviceListPresenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mDeviceListPresenter = new DeviceListPresenter(mContext, mView, mScannerContract);
    }

    @Test
    public void beginSearch() throws Exception {
        // start search
        mDeviceListPresenter.beginSearch();
        // verify that loader is hidden
        verify(mView).displayLoader();
        // verify that scanner has started search
        verify(mScannerContract).startScan(mDeviceListPresenter);
    }

    @Test
    public void getDevices() throws Exception {
        List<Device> deviceList = new ArrayList<>();

        // get devices from WifiScanner
        mDeviceListPresenter.getDevices(new ArrayList<ScanResult>());
        //verify that loader is hidden

        verify(mView).hideLoader();
        // check if devices are displayed
        verify(mView).displayDevices(deviceList);

    }

    @Test
    public void refresh() throws Exception {
        mDeviceListPresenter.refresh();

        verify(mView).displayLoader();
        verify(mScannerContract).startScan(mDeviceListPresenter);
    }

    @Test
    public void onViewAttached() throws Exception {
    }

}