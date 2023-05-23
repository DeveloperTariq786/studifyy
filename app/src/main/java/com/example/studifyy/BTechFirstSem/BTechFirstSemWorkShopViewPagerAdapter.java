package com.example.studifyy.BTechFirstSem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BTechFirstSemWorkShopViewPagerAdapter extends FragmentPagerAdapter {
    public BTechFirstSemWorkShopViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
            return new BTechFirstSemWorkShopNotesFragment();

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
     return "Notes";
    }
}
