package com.ericcode.rxhttpdemo.rx.transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class ThrowExceptionTransformer<T> implements KeepTypeTransformer<T> {

    private ThrowExceptionTransformer() {
    }

    public static <T> ThrowExceptionTransformer<T> create() {
        return new ThrowExceptionTransformer<>();
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .map(t -> {
                    if (true) {
                        throw new RuntimeException("make exception");
                    }
                    return t;
                });
    }
}
