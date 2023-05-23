package com.example.studifyy.BTechSecondSem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BTechSecondSemMechanicslViewPagerAdapter extends FragmentPagerAdapter {
    public BTechSecondSemMechanicslViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position==0) {
            return new BTechSecondSemMechanicsNotesFragment();
        }
        else return null;

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Notes";
        } else  {
            return null;
        }
    }
}
