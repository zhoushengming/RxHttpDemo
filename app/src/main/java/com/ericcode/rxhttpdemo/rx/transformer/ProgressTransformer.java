package com.ericcode.rxhttpdemo.rx.transformer;

import android.app.Activity;
import android.app.ProgressDialog;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class ProgressTransformer<T> implements KeepTypeTransformer<T> {
    private Subject<Boolean> subject = PublishSubject.create();
    private ProgressDialog progressDialog;

    private ProgressTransformer(Activity activity) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setOnCancelListener(dialog -> {
            subject.onNext(true);//如果取消隐藏当前Dialog，则解除订阅关系
        });
    }

    public static <T> ProgressTransformer<T> create(Activity activity) {
        return create(activity, true);
    }

    public static <T> ProgressTransformer<T> create(Activity activity, boolean cancelable) {
        ProgressTransformer<T> transformer = new ProgressTransformer<>(activity);
        transformer.progressDialog.setCancelable(cancelable);
        return transformer;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.takeUntil(subject.filter(it -> it).take(1))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(t -> show())
                .doOnComplete(this::dismiss)
                .doOnError(e -> dismiss());
    }

    private void dismiss() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void show() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }
}
