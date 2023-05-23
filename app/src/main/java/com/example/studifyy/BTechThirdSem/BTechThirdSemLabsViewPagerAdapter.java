package com.example.studifyy.BTechThirdSem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class BTechThirdSemLabsViewPagerAdapter extends FragmentPagerAdapter {

    public BTechThirdSemLabsViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       if (position==0){
           return new BTechThirdSemDSLabFragment();
       } else if (position==1) {
           return new BTechThirdSemDBLabFragment();
       }
       else {
           return new BTechThirdSemElectronicLabFragment();
       }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "DS-LAB";
        } else if (position==1) {
            return "DB-LAB";
        }
        else {
            return "ELECTRONIC-LAB";
        }
    }
}
