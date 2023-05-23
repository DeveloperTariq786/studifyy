package com.example.studifyy.BTechFirstSem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class BTechFirstSemLabViewPagerAdapter extends FragmentPagerAdapter {

    public BTechFirstSemLabViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0){
            return new BTechFirstSemCpLabFragment();
        } else if (position==1) {
            return new BTechFirstSemPhysicsLabFragment();
        }
        else {
            return new BTechFirstSemChemistryLabFragment();
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
            return "Cp-Lab";
        } else if (position==1) {
            return "Physics-Lab";
        }
        else {
            return "Chemistry_Lab";
        }
    }
}
