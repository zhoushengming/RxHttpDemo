package com.ericcode.rxhttpdemo.rx.transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SubscribeOnIOTransformer<T> implements KeepTypeTransformer<T> {

    private SubscribeOnIOTransformer() {
    }

    public static <T> SubscribeOnIOTransformer<T> create() {
        return new SubscribeOnIOTransformer<>();
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io());
    }
}
