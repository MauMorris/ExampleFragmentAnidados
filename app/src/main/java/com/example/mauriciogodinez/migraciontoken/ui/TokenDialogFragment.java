package com.example.mauriciogodinez.migraciontoken.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mauriciogodinez.migraciontoken.anim.PageTransformEjemplo;
import com.example.mauriciogodinez.migraciontoken.R;
import com.example.mauriciogodinez.migraciontoken.databinding.AlertTokenViewpagerBinding;
import com.example.mauriciogodinez.migraciontoken.impl.TokenDialogImpl;
import com.example.mauriciogodinez.migraciontoken.ui.utils.TokenAdapter;

/*
 * Created by mauriciogodinez on 07/10/17.
 */
public class TokenDialogFragment extends DialogFragment {
    private TokenAdapter mAdapter;

    private TokenDialogImpl mPresenter;

    private AlertTokenViewpagerBinding mDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         mDataBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.alert_token_viewpager,
                container,
                false);

        View view = mDataBinding.getRoot();

        mPresenter = new TokenDialogImpl(getContext(), this);

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
        mDataBinding.botonCerrar.setOnClickListener(mPresenter.clicCerrar());
        mDataBinding.botonSiguienteAlert.setOnClickListener(mPresenter.clicSiguiente(mDataBinding.viewpagerAlert));

        mDataBinding.viewpagerAlert.addOnPageChangeListener(
                mPresenter.pageChange(getActivity(),
                        mAdapter,
                        mDataBinding.viewpagerAlert,
                        mDataBinding.botonSiguienteAlert));
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