package com.arzirtime.remote.client.ui.device.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.arzirtime.remote.client.ui.device.DeviceCategroyTabFragment;
import com.arzirtime.remote.client.ui.device.DevicePositionFragment;

import java.util.ArrayList;
import java.util.List;

public class DeviceCenterPagerAdapter extends FragmentPagerAdapter {
    private static final String[] fragmentsTitles = new String[]{"设备", "位置"};
    private List<Fragment> fragments = new ArrayList<Fragment>();

    public DeviceCenterPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> fragments) {
        super(fm, behavior);
        this.fragments = fragments;

        initFragments();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {//选择性实现
        return fragmentsTitles[position];
    }

    private void initFragments() {
        fragments.add(new DeviceCategroyTabFragment());
        fragments.add(new DevicePositionFragment());
    }
}
