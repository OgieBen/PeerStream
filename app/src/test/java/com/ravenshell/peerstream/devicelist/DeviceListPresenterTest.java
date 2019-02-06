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

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Mock
    OutputStream mStream;

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

    @Test
    public void experiment() throws Exception {

        // simple test used to experiment with when...thenReturn apis
        Comparable<Integer> c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);

        assertEquals(-1, c.compareTo(3));


        // using spy
        Properties prop = mock(Properties.class);
        Properties spyProp = spy(prop);

        // used to throw exceptions
        when(prop.get(anyString())).thenThrow(new IllegalArgumentException());

        try{
            prop.get(anyString());
        }catch (IllegalArgumentException e){

        }

        // do return 32 when spyProp calls get("Elite")
        doReturn(32).when(spyProp).get("delibrate");
        // doThrow(new IllegalArgumentException()).when(spyProp).store(mStream, "delibrate");
        // verify(prop).store(mStream, "delibrate");


        assertEquals(32, spyProp.get("delibrate"));
    }





}