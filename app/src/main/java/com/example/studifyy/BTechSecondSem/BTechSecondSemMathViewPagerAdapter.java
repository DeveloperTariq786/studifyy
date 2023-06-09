package com.example.studifyy.BTechSecondSem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.studifyy.BTechFirstSem.BTechFirstSemMathBooksFragment;

public class BTechSecondSemMathViewPagerAdapter extends FragmentPagerAdapter {
    public BTechSecondSemMathViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new BTechFirstSemMathBooksFragment();
        }
        else  {
            return new BTechSecondSemMathNotesFragment();
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
            return "Books";
        } else  {
            return "Notes";
        }
    }
}
