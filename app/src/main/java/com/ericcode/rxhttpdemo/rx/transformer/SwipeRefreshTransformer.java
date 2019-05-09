package com.ericcode.rxhttpdemo.rx.transformer;

import android.support.v4.widget.SwipeRefreshLayout;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class SwipeRefreshTransformer<T> implements ObservableTransformer<T, T> {

    private SwipeRefreshLayout swipeRefreshLayout;

    private SwipeRefreshTransformer(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public static <T> SwipeRefreshTransformer<T> create(SwipeRefreshLayout swipeRefreshLayout) {
        return new SwipeRefreshTransformer<T>(swipeRefreshLayout);
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .doOnSubscribe(disposable -> swipeRefreshLayout.setRefreshing(true))
                .doOnComplete(() -> swipeRefreshLayout.setRefreshing(false))
                .doOnError(e -> swipeRefreshLayout.setRefreshing(false));
    }
}
