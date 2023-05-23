package com.example.studifyy.BTechSecondSem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BTechSecondSemLabViewPagerAdapter extends FragmentPagerAdapter {
    public BTechSecondSemLabViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new BTechSecondSemElectricalLabFragment();
        }
        else {
            return new BTechSecondSemOopsLabFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Electrical-Lab";
        }  else {
            return "Oops-Lab";
        }
    }
}
