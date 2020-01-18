package com.example.mauriciogodinez.migraciontoken.constructor;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;

import com.example.mauriciogodinez.migraciontoken.ui.utils.TokenAdapter;

/*
 * Created by mauriciogodinez on 10/12/17.
 */

public interface TokenDialogView {
    void windowFormat(Window window);

    View.OnClickListener clicSiguiente(ViewPager viewPager);
    View.OnClickListener clicCerrar();
    ViewPager.OnPageChangeListener pageChange(Context context, TokenAdapter adapter, ViewPager viewPager, AppCompatButton dialogButton);
    void onDestroy();
}