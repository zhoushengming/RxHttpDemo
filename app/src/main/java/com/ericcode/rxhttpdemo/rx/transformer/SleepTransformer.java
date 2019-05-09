package com.ericcode.rxhttpdemo.rx.transformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class SleepTransformer<T> implements KeepTypeTransformer<T> {
    private long timeMillis;

    private SleepTransformer(long time) {
        this.timeMillis = time;
    }

    public static <T> SleepTransformer<T> create(long timeMillis) {
        return new SleepTransformer<>(timeMillis);
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .map(t -> {
                    Thread.sleep(timeMillis);
                    return t;
                });
    }
}
