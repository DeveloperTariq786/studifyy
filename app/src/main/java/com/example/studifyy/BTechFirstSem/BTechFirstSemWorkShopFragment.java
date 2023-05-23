package com.example.studifyy.BTechFirstSem;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studifyy.R;
import com.google.android.material.tabs.TabLayout;

public class BTechFirstSemWorkShopFragment extends Fragment {
    TabLayout Tabs;
    ViewPager viewPager;
    public BTechFirstSemWorkShopFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_b_tech_first_sem_work_shop, container, false);
        Tabs=view.findViewById(R.id.Tab1);
        viewPager=view.findViewById(R.id.viewpager1);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.setAdapter(new BTechFirstSemWorkShopViewPagerAdapter(getChildFragmentManager()));
        Tabs.setupWithViewPager(viewPager);

    }
}