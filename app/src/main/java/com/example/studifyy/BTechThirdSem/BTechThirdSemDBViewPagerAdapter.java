package com.example.studifyy.BTechThirdSem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class BTechThirdSemDBViewPagerAdapter extends FragmentPagerAdapter {
    public BTechThirdSemDBViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       if (position==0){
           return new BTechThirdSemDBNotesFragment();
       } else  {
           return null;
       }

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return "Notes";
        } else  {
            return null;
        }

    }
}
