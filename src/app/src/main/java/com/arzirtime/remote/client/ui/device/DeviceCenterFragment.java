package com.arzirtime.remote.client.ui.device;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arzirtime.remote.R;
import com.arzirtime.remote.client.ui.MainActivity;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class DeviceCenterFragment extends Fragment {
    private View view;
    private MainActivity parentActivity;

    /*--------------------------------------------------------------------------
     * MagicIndicator 导航栏
     *     Meven： https://jitpack.io/#hackware1993/MagicIndicator/1.5.0
     *     GitHub：https://github.com/hackware1993/MagicIndicator
     *     blog：  https://www.jianshu.com/u/f0a3fb1abaed
     * --------------------------------------------------------------------------*/
    private static final String[] fragmentsTitles = new String[]{"设备", "位置"};
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();

    //region Fragment meber funtion

    public DeviceCenterFragment() {

    }

    public static DeviceCenterFragment newInstance() {
        DeviceCenterFragment fragment = new DeviceCenterFragment();
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.parentActivity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_device_center, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
    }

    //endregion

    private void initViews() {
        parentActivity.initAfterFragmentViewCreated(this); //Fragment的ToolBar、相关界面
        initTabFragments();
        initMagicIndicator();
        setDefaultPage(0);
    }

    //region MagicIndicator

    private void initTabFragments() {
        mFragments.add(new DeviceCategroyTabFragment());
        mFragments.add(new DevicePositionFragment());
    }

    private void initMagicIndicator() {
        MagicIndicator magicIndicator = (MagicIndicator) view.findViewById(R.id.device_center_magic_indicator);
        //magicIndicator.setBackgroundColor(Color.BLACK);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        //commonNavigator.setScrollPivotX(0.25f);
/*
        int padding = UIUtil.getScreenWidth(getContext()) / 4;
        commonNavigator.setRightPadding(padding);
        commonNavigator.setLeftPadding(padding);*/

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return fragmentsTitles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Color.parseColor("#FFA5C0C5"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#FF03515D"));
                simplePagerTitleView.setText(fragmentsTitles[index]);
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragmentContainerHelper.handlePageSelected(index);
                        switchPages(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(Color.parseColor("#FFA5C0C5"));
                return linePagerIndicator;
            }

        });
        magicIndicator.setNavigator(commonNavigator);
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);

    }

    private void setDefaultPage(int index){
        mFragmentContainerHelper.handlePageSelected(index, false);
        switchPages(index);
    }

    private void switchPages(int index) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        for (int i = 0, j = mFragments.size(); i < j; i++) {
            if (i == index) {
                continue;
            }
            fragment = mFragments.get(i);
            if (fragment.isAdded()) {
                fragmentTransaction.hide(fragment);
            }
        }
        fragment = mFragments.get(index);
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.device_center_fragment_container, fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }


    //endregion
}
