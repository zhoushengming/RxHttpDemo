package com.ericcode.rxhttpdemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.ericcode.rxhttpdemo.util.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.RxActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;

public class ActivityHolder {
    private final ArrayList<SoftReference<LifecycleProvider>> createdActivities = new ArrayList<>();
    private volatile static ActivityHolder ins;

    private ActivityHolder() {
    }

    public static ActivityHolder ins() {
        if (ins == null) {
            synchronized (ActivityHolder.class) {
                if (ins == null) {
                    ins = new ActivityHolder();
                }
            }
        }
        return ins;
    }

    public ArrayList<SoftReference<LifecycleProvider>> getCreatedActivities() {
        return createdActivities;
    }

    public void init(Application app) {
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Logger.i("onActivityCreated() activity:" + activity);
                if (activity instanceof LifecycleProvider) {
                    createdActivities.add(0, new SoftReference<>((LifecycleProvider) activity));
                    Logger.i("onActivityCreated() holder activity:" + activity);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Logger.i("onActivityDestroyed() activity:" + activity);
                if (activity instanceof LifecycleProvider) {
                    Iterator<SoftReference<LifecycleProvider>> iterator = createdActivities.iterator();
                    ArrayList<SoftReference<LifecycleProvider>> removeList = new ArrayList<>();
                    while (iterator.hasNext()) {
                        SoftReference<LifecycleProvider> next = iterator.next();
                        LifecycleProvider lifecycleProvider = next.get();
                        if (lifecycleProvider == null) {
                            iterator.remove();
                        } else {
                            if (lifecycleProvider == activity) {
                                removeList.add(next);
                            }
                        }
                    }
                    createdActivities.removeAll(removeList);
                    Logger.i("onActivityDestroyed() release activity:" + activity);
                }
            }
        });
    }
}
