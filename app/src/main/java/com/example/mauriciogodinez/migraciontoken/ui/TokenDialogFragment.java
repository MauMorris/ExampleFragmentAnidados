package com.example.mauriciogodinez.migraciontoken.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mauriciogodinez.migraciontoken.anim.PageTransformEjemplo;
import com.example.mauriciogodinez.migraciontoken.R;
import com.example.mauriciogodinez.migraciontoken.impl.TokenDialogImpl;
import com.example.mauriciogodinez.migraciontoken.utils.TokenAdapter;

/*
 * Created by mauriciogodinez on 07/10/17.
 */
public class TokenDialogFragment extends DialogFragment {
    private AppCompatButton bDialogo, bCerrar;

    private ViewPager mViewpager;
    private TokenAdapter mAdapter;

    private TokenDialogImpl mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alert_token_viewpager,container);

        bCerrar = view.findViewById(R.id.boton_cerrar);
        bDialogo = view.findViewById(R.id.boton_alert);
        mViewpager = view.findViewById(R.id.viewpager_alert);

        mAdapter = new TokenAdapter(getChildFragmentManager());

        mPresenter = new TokenDialogImpl(getContext(), this);

        bDialogo.setText(getResources().getText(R.string.texto_siguiente));
        mViewpager.setAdapter(mAdapter);

        bCerrar.setOnClickListener(mPresenter.clicCerrar());
        bDialogo.setOnClickListener(mPresenter.clicSiguiente(mViewpager));
        mViewpager.addOnPageChangeListener(mPresenter.pageChange(getActivity(), mAdapter, mViewpager, bDialogo));

        mViewpager.setPageTransformer(true, new PageTransformEjemplo());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.windowFormat(getDialog().getWindow());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mPresenter.onDestroy();
    }
}