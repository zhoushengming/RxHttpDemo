package com.ericcode.rxhttpdemo.rx.transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IO2MainTransformer<T> implements KeepTypeTransformer<T> {

    private IO2MainTransformer() {
    }

    public static <T> IO2MainTransformer<T> create() {
        return new IO2MainTransformer<>();
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
