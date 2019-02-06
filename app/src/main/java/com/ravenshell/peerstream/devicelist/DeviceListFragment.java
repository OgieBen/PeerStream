package com.ravenshell.peerstream.devicelist;


import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ravenshell.peerstream.R;
import com.ravenshell.peerstream.adapters.DevicesRecyclerViewAdapter;
import com.ravenshell.peerstream.utils.broadcastreceivers.WifiP2pBroadcastReceiver;
import com.ravenshell.peerstream.wificonnector.scanner.WifiScanner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeviceListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeviceListFragment extends Fragment implements DeviceListContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DeviceListPresenter mPresenter;
    private IntentFilter mIntentFilter;
    private WifiP2pBroadcastReceiver mP2pBroadcastReceiver;
    private WifiP2pManager mWifiP2pManager;
    private WifiP2pManager.Channel mChannel;
    private RecyclerView mDeviceRecyclerView;
    private DevicesRecyclerViewAdapter mAdapter;


    public DeviceListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeviceListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeviceListFragment newInstance(String param1, String param2) {
        DeviceListFragment fragment = new DeviceListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mPresenter = new DeviceListPresenter(getContext(), this, new WifiScanner(getContext(), new IntentFilter()));
        mAdapter = new DevicesRecyclerViewAdapter(new ArrayList<WifiP2pDevice>(0));
        setListeners();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDeviceRecyclerView = (RecyclerView)view.findViewById(R.id.devices_recyclerview);
        mPresenter.beginSearch();
    }

    @Override
    public void onResume() {
        super.onResume();
        mP2pBroadcastReceiver = new WifiP2pBroadcastReceiver(mWifiP2pManager, mChannel, mPresenter);
        getContext().registerReceiver(mP2pBroadcastReceiver, mIntentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(mP2pBroadcastReceiver);
    }

    public void setIsWifiEnabled(Boolean state){

    }

    @Override
    public void displayDevices(List<WifiP2pDevice> devices) {
        if (devices != null){
            Toast.makeText(getContext(), "Found " + devices.size() + " devices", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(getContext(), "Error finding device", Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void displayLoader() {

    }

    @Override
    public void displayMsg(String str) {

    }


    void setListeners() {
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        mWifiP2pManager = (WifiP2pManager) getContext().getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mWifiP2pManager.initialize(getContext(), getActivity().getMainLooper(), null );
    }


}
