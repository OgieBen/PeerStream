package com.ravenshell.peerstream.adapters;

import android.net.wifi.p2p.WifiP2pDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ravenshell.peerstream.R;
import com.ravenshell.peerstream.devicelist.DeviceListContract;
import com.ravenshell.peerstream.utils.helpers.RecyclerViewHelper;

import java.util.List;

public class DevicesRecyclerViewAdapter extends RecyclerView.Adapter<DevicesRecyclerViewAdapter.ViewHolder>  {


    private DeviceListContract.View mView;
    private List<WifiP2pDevice> mDevices;

    public DevicesRecyclerViewAdapter(List<WifiP2pDevice> devices) {
        mDevices = devices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scan_result_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        View itemView = viewHolder.mInnerItemView;
        RecyclerViewHelper.updateDeviceListView(itemView, mDevices.get(i));
    }

    @Override
    public int getItemCount() {
        return mDevices.size();
    }

    public void replaceDevices(List<WifiP2pDevice> devices){
        mDevices.clear();
        mDevices.addAll(devices);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected View mInnerItemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mInnerItemView = itemView;
        }
    }
}
