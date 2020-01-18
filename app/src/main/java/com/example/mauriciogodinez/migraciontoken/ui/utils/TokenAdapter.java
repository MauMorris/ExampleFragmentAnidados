package com.example.mauriciogodinez.migraciontoken.ui.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mauriciogodinez.migraciontoken.R;

/*
 * Created by mauriciogodinez on 07/10/17.
 */

public class TokenAdapter extends FragmentPagerAdapter {

    private Bundle mBundle;
    private TokenFragment fToken;

    public TokenAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        mBundle = new Bundle();
        fToken = new TokenFragment();

        switch (position){
            case 0:
                mBundle.putInt("page", R.layout.fragment_a);
                fToken.setArguments(mBundle);

                return fToken;
            case 1:
                mBundle.putInt("page", R.layout.fragment_b);
                fToken.setArguments(mBundle);

                return fToken;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}