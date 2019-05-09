package com.ericcode.rxhttpdemo.rx;

import com.ericcode.rxhttpdemo.util.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Observer的默认实现
 * 一般调试使用
 * 不允许继承 重写
 *
 * @param <T>
 */
public final class DefaultObserver<T> implements Observer<T> {

    private DefaultObserver(){}

    public static <T> Observer<T> create(){
        return new DefaultObserver<T>();
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        Logger.i(t.toString());
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        Logger.i("onComplete");
    }


}
