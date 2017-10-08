package com.example.mauriciogodinez.migraciontoken;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Layout;
import android.view.View;

/*
 * Created by mauriciogodinez on 07/10/17.
 */

public class TokenAdapter extends FragmentPagerAdapter {

    public TokenAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        TokenFragment tokenF = new TokenFragment();

        switch (position){
            case 0:
                bundle.putInt("page", R.layout.fragment_a);
                tokenF.setArguments(bundle);
                return tokenF;
            case 1:
                bundle.putInt("page", R.layout.fragment_b);
                tokenF.setArguments(bundle);
                return tokenF;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}