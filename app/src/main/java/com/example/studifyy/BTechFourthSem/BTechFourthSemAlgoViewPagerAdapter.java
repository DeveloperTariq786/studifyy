package com.example.studifyy.BTechFourthSem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.studifyy.BTechFirstSem.BTechFirstSemCpBooksFragment;
import com.example.studifyy.BTechFirstSem.BTechFirstSemCpNotesFragment;
import com.example.studifyy.BTechFirstSem.BTechFirstSemCpPapersFragment;
public class BTechFourthSemAlgoViewPagerAdapter extends FragmentPagerAdapter {
    public BTechFourthSemAlgoViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new BTechFourthSemAlgoBooksFragment();
        }
        else  {
            return new BTechFourthSemAlgoNotesFragment();
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
