package com.ericcode.rxhttpdemo;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ericcode.rxhttpdemo.base.BaseFragment;
import com.ericcode.rxhttpdemo.fragment.MainPagerCommonFragment;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<BaseFragment> fragments;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new MainPagerCommonFragment().setTitle("最热"));
        fragments.add(new MainPagerCommonFragment().setTitle("最新"));
        fragments.add(new MainPagerCommonFragment().setTitle("节点"));
        fragments.add(new MainPagerCommonFragment().setTitle("节点"));
        fragments.add(new MainPagerCommonFragment().setTitle("节点"));
        fragments.add(new MainPagerCommonFragment().setTitle("节点"));
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
