package com.example.mauriciogodinez.migraciontoken;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

/*
 * Created by mauriciogodinez on 07/10/17.
 */
public class TokenDialogFragment extends DialogFragment {
    private AppCompatButton botonDialogo;

    private ViewPager mViewpager;
    private TokenAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alert_token_viewpager,container);

        AppCompatButton botonCerrar = view.findViewById(R.id.boton_cerrar);

        botonDialogo = view.findViewById(R.id.boton_alert);
        mViewpager = view.findViewById(R.id.viewpager_alert);

        botonCerrar.setOnClickListener(clickCerrar);

        botonDialogo.setText(getResources().getText(R.string.texto_siguiente));
        botonDialogo.setOnClickListener(clickSiguiente);

        mAdapter = new TokenAdapter(getChildFragmentManager());

        mViewpager.setAdapter(mAdapter);
        mViewpager.addOnPageChangeListener(pageChange);
        mViewpager.setPageTransformer(true, new PageTransformEjemplo());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();

        if (window != null) {
            window.setLayout(getResources().getDimensionPixelSize(R.dimen.token_w), getResources().getDimensionPixelSize(R.dimen.token_h));
            window.setGravity(Gravity.CENTER);
        }
    }

    private View.OnClickListener clickCerrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    };

    private ViewPager.OnPageChangeListener pageChange = new ViewPager.OnPageChangeListener() {
        Integer[] colors = {Color.MAGENTA, Color.GRAY};
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position < (mAdapter.getCount() - 1) && position < (colors.length - 1)) {
                mViewpager.setBackgroundColor((Integer) new ArgbEvaluator().evaluate(positionOffset, colors[position], colors[position + 1]));
            } else {
                mViewpager.setBackgroundColor(colors[colors.length - 1]);
            }
        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    botonDialogo.setText(getResources().getText(R.string.texto_siguiente));
                    botonDialogo.setOnClickListener(clickSiguiente);
                    break;
                case 1:
                    botonDialogo.setText(getResources().getText(R.string.texto_activar_token));
                    botonDialogo.setOnClickListener(clickShowToken);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    View.OnClickListener clickSiguiente = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mViewpager.setCurrentItem(mViewpager.getCurrentItem() + 1, true);
        }
    };

    View.OnClickListener clickShowToken = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Show Token", Toast.LENGTH_SHORT).show();
            dismiss();
        }
    };
}