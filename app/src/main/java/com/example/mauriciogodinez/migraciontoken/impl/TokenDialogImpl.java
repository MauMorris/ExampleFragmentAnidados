package com.example.mauriciogodinez.migraciontoken.impl;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.mauriciogodinez.migraciontoken.R;
import com.example.mauriciogodinez.migraciontoken.constructor.TokenDialogView;
import com.example.mauriciogodinez.migraciontoken.ui.TokenDialogFragment;
import com.example.mauriciogodinez.migraciontoken.ui.utils.TokenAdapter;

/*
 * Created by mauriciogodinez on 10/12/17.
 */

public class TokenDialogImpl implements TokenDialogView{
    private static final String LOG_TAG = TokenDialogImpl.class.getSimpleName();

    private Context mContext;
    private TokenDialogFragment mTokenDialog;
    private View.OnClickListener cerrar, siguiente, showToken;
    private ViewPager.OnPageChangeListener pageChange;

    public TokenDialogImpl(Context mContext, TokenDialogFragment mTokenDialog) {
        this.mContext = mContext;
        this.mTokenDialog = mTokenDialog;
    }

    @Override
    public void windowFormat(Window window) {
        if(mContext != null && window != null) {
                window.setLayout(mContext.getResources().getDimensionPixelSize(R.dimen.token_w), mContext.getResources().getDimensionPixelSize(R.dimen.token_h));
                window.setGravity(Gravity.CENTER);
        } else
            Log.d(LOG_TAG, ": Contexto y ventana no definidos, no se puede validar la ventana");
    }

    @Override
    public View.OnClickListener clicSiguiente(final ViewPager viewPager) {

        return siguiente = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                } catch (Exception e){
                    Log.d(LOG_TAG, e.getMessage());
                    viewPager.setCurrentItem(viewPager.getCurrentItem(), true);
                }
            }
        };
    }

    @Override
    public View.OnClickListener clicCerrar() {
        return cerrar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTokenDialog != null)
                    mTokenDialog.dismiss();
            }
        };
    }

    private View.OnClickListener clicShowToken(final Context context, final  String mensaje, final int toastLength) {
        return showToken = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toastLength == Toast.LENGTH_LONG || toastLength == Toast.LENGTH_SHORT)
                    Toast.makeText(context, mensaje, toastLength).show();
                else
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();

                if(mTokenDialog != null)
                    mTokenDialog.dismiss();
            }
        };
    }

    @Override
    public ViewPager.OnPageChangeListener pageChange(final Context context, final TokenAdapter adapter, final ViewPager viewPager, final AppCompatButton dialogButton) {
        return pageChange = new ViewPager.OnPageChangeListener() {
            Integer[] colors = {Color.MAGENTA, Color.GRAY};

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor((Integer) new ArgbEvaluator().evaluate(positionOffset, colors[position], colors[position + 1]));
                } else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        dialogButton.setText(mContext.getResources().getText(R.string.texto_siguiente));
                        dialogButton.setOnClickListener(clicSiguiente(viewPager));
                        break;
                    case 1:
                        dialogButton.setText(mContext.getResources().getText(R.string.texto_activar_token));
                        dialogButton.setOnClickListener(clicShowToken(context, "Muestra Actividad para Migrar Token", Toast.LENGTH_SHORT));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
    }

    @Override
    public void onDestroy() {
        mContext = null;
        mTokenDialog = null;
        cerrar = null;
        siguiente = null;
        showToken = null;
        pageChange = null;
    }
}