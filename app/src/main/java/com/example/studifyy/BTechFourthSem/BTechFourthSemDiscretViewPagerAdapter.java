package com.example.studifyy.BTechFourthSem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BTechFourthSemDiscretViewPagerAdapter extends FragmentPagerAdapter {
    public BTechFourthSemDiscretViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new BTechFourthSemDiscretBooksFragment();
        }
        else  {
            return new BTechFourthSemDiscretNotesFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Books";
        }
        else {
            return "Notes";
        }

    }
}
