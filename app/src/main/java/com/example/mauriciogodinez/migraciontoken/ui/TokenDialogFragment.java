package com.example.mauriciogodinez.migraciontoken.ui;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mauriciogodinez.migraciontoken.ui.anim.PageTransformEjemplo;
import com.example.mauriciogodinez.migraciontoken.R;
import com.example.mauriciogodinez.migraciontoken.databinding.AlertTokenViewpagerBinding;
import com.example.mauriciogodinez.migraciontoken.ui.utils.TokenAdapter;

/*
 * Created by mauriciogodinez on 07/10/17.
 */
public class TokenDialogFragment extends DialogFragment {
    private static final String LOG_TAG = TokenDialogFragment.class.getSimpleName();
    private TokenAdapter mAdapter;

    private AlertTokenViewpagerBinding mDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mDataBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.alert_token_viewpager,
                container,
                false);

        View view = mDataBinding.getRoot();

        setViews(mDataBinding);
        setListeners(mDataBinding);

        return view;
    }

    private void setViews(AlertTokenViewpagerBinding mDataBinding) {
        mDataBinding.botonSiguienteAlert.setText(getResources().getText(R.string.texto_siguiente));

        mAdapter = new TokenAdapter(getChildFragmentManager());

        mDataBinding.viewpagerAlert.setAdapter(mAdapter);
        mDataBinding.viewpagerAlert.setPageTransformer(true, new PageTransformEjemplo());
    }

    private void setListeners(AlertTokenViewpagerBinding mDataBinding) {
        mDataBinding.botonCerrar.setOnClickListener(cerrar);
        mDataBinding.botonSiguienteAlert.setOnClickListener(siguiente);

        mDataBinding.viewpagerAlert.addOnPageChangeListener(pageChange);
    }

    View.OnClickListener siguiente = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int currentItem = mDataBinding.viewpagerAlert.getCurrentItem();
            try {
                mDataBinding.viewpagerAlert.setCurrentItem(currentItem + 1, true);
            } catch (Exception e) {
                Log.d(LOG_TAG, e.getMessage());
                mDataBinding.viewpagerAlert.setCurrentItem(currentItem, true);
            }
        }
    };

    View.OnClickListener cerrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    };

    ViewPager.OnPageChangeListener pageChange = new ViewPager.OnPageChangeListener() {
        Integer[] colors = {Color.MAGENTA, Color.GRAY};

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position < (mAdapter.getCount() - 1) && position < (colors.length - 1)) {
                mDataBinding.viewpagerAlert.setBackgroundColor(
                        (Integer) new ArgbEvaluator()
                                .evaluate(
                                        positionOffset,
                                        colors[position],
                                        colors[position + 1]));
            } else {
                mDataBinding.viewpagerAlert.setBackgroundColor(colors[colors.length - 1]);
            }
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mDataBinding.botonSiguienteAlert.setText(getResources().getText(R.string.texto_siguiente));
                    mDataBinding.botonSiguienteAlert.setOnClickListener(siguiente);
                    break;
                case 1:
                    mDataBinding.botonSiguienteAlert.setText(getResources().getText(R.string.texto_activar_token));
                    mDataBinding.botonSiguienteAlert.setOnClickListener(clicShowToken(
                            getContext(),
                            "Muestra Actividad para Migrar Token",
                            Toast.LENGTH_SHORT));
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    View.OnClickListener clicShowToken(final Context context, final String mensaje, final int toastLength) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toastLength == Toast.LENGTH_LONG || toastLength == Toast.LENGTH_SHORT)
                    Toast.makeText(context, mensaje, toastLength).show();
                else
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();

                dismiss();
            }
        };
    }
}