package com.ericcode.rxhttpdemo.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ericcode.rxhttpdemo.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends RxFragment {
    private static final String KEY_TITLE = "KEY_TITLE";
    Unbinder unbinder;

    public BaseFragment setTitle(String title) {
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putString(KEY_TITLE, title);
        setArguments(arguments);
        return this;
    }

    public String getTitle() {
        return getArguments() != null ? getArguments().getString(KEY_TITLE, "") : "";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract @LayoutRes int getLayoutId();
}
