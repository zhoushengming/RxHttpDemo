package com.ericcode.rxhttpdemo.rx.transformer;

import io.reactivex.ObservableTransformer;

public interface KeepTypeTransformer<T> extends ObservableTransformer<T, T> {

}
