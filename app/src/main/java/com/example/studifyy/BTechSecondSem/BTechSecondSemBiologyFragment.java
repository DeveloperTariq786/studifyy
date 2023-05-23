package com.example.studifyy.BTechSecondSem;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studifyy.R;
import com.google.android.material.tabs.TabLayout;


public class BTechSecondSemBiologyFragment extends Fragment {
    TabLayout Tabs;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_b_tech_second_sem_biology, container, false);
        Tabs = view.findViewById(R.id.Tab1);
        viewPager = view.findViewById(R.id.viewpager1);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager.setAdapter(new BTechSecondSemBiologyViewPagerAdapter (getChildFragmentManager()));
        Tabs.setupWithViewPager(viewPager);
    }
}