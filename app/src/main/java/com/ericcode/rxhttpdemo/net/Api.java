package com.ericcode.rxhttpdemo.net;

import com.ericcode.rxhttpdemo.ActivityHolder;
import com.ericcode.rxhttpdemo.base.BaseFragment;
import com.ericcode.rxhttpdemo.net.api.V2exRepo;
import com.ericcode.rxhttpdemo.rx.transformer.IO2MainTransformer;
import com.ericcode.rxhttpdemo.rx.transformer.KeepTypeTransformer;
import com.ericcode.rxhttpdemo.rx.transformer.SleepTransformer;
import com.ericcode.rxhttpdemo.rx.transformer.SubscribeOnIOTransformer;
import com.ericcode.rxhttpdemo.util.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.RxActivity;

import java.lang.ref.SoftReference;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class Api {
    private static V2exRepo v2exRepo;

    private static List<Class<? extends ObservableTransformer>> keepTypeTransformers
            = new ArrayList<Class<? extends ObservableTransformer>>() {{
        add(KeepTypeTransformer.class);  // 保持Observable类型
        add(LifecycleTransformer.class);  // RxLifecycle
    }};

    private static void initV2Repo() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.v2ex.com/")
                .client(okHttpClient)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        v2exRepo = retrofit.create(V2exRepo.class);
    }

    private static V2exRepo getV2exRepo() {
        if (v2exRepo == null) {
            initV2Repo();
        }
        return v2exRepo;
    }

    public static V2exRepo v2exRepo(ObservableTransformer... transformers) {
        V2exRepo v2exRepo = getV2exRepo();
//        v2exRepo = subscribeOnIO(v2exRepo);
        return injectTransforms(v2exRepo, transformers);
    }

    public static V2exRepo v2exRepo() {
        Class callerClass = getCallerClass();
        Logger.i("v2exRepo() callerClass:" + callerClass);
        ArrayList<ObservableTransformer> transformers = new ArrayList<>();
        transformers.add(SleepTransformer.create(300));
        transformers.add(IO2MainTransformer.create());
        if (LifecycleProvider.class.isAssignableFrom(callerClass)) {
            ArrayList<SoftReference<LifecycleProvider>> createdActivities = ActivityHolder.ins().getCreatedActivities();
            for (int i = 0; i < createdActivities.size(); i++) {
                SoftReference<LifecycleProvider> rxActivitySoftReference = createdActivities.get(i);
                LifecycleProvider lifecycleProvider = rxActivitySoftReference.get();
                if (lifecycleProvider != null && lifecycleProvider.getClass() == callerClass) {
                    Logger.i("v2exRepo() bingo");
                    LifecycleTransformer lifecycleTransformer = lifecycleProvider.bindToLifecycle();
                    transformers.add(lifecycleTransformer);
                }
            }
        } else {
            Logger.w("current caller is not LifecycleProvider instance");
        }
        V2exRepo v2exRepo = getV2exRepo();
        ObservableTransformer[] transformersArr = new ObservableTransformer[transformers.size()];
        transformersArr = transformers.toArray(transformersArr);
        return injectTransforms(v2exRepo, transformersArr);
    }

    private static Class getCallerClass() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[2];
        String className = stackTraceElement.getClassName();
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cls;
    }

    public static V2exRepo v2exRepo(BaseFragment baseFragment) {
        return injectTransforms(getV2exRepo(),
                IO2MainTransformer.create(),
                baseFragment.bindToLifecycle());
    }

    /**
     * 一般网络请求需要使用这个方法，将subscribe切换到IO线程
     */
    private static <T> T subscribeOnIO(T t) {
        return injectTransforms(t, SubscribeOnIOTransformer.create());
    }

    private static <T> T injectTransforms(T obj, ObservableTransformer... transformers) {
        checkTransformers(transformers);
        Object proxyInstance = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    Object invoke = method.invoke(obj, args);
                    Class<?> returnType = invoke.getClass();
                    // 如果返回Observable
                    if (Observable.class.isAssignableFrom(returnType)) {
//                        Logger.i("is Observable");
                        Observable observable = (Observable) invoke;
                        for (ObservableTransformer transformer : transformers) {
//                            Logger.i("compose:" + transformer.getClass().getName());
                            observable = observable.compose(transformer);
                        }
                        return observable;
                    }
                    return invoke;
                });
        if (obj.getClass().isInstance(proxyInstance)) {
            return (T) proxyInstance;
        }
        return null;
    }

    private static void checkTransformers(ObservableTransformer[] transformers) {
        for (ObservableTransformer transformer : transformers) {
            Class<? extends ObservableTransformer> transformerClass = transformer.getClass();
            boolean illegalTransformer = true;
            for (Class<? extends ObservableTransformer> keepTypeTransformer : keepTypeTransformers) {
                if (keepTypeTransformer == transformerClass) {
                    illegalTransformer = false;
                    continue;
                }
                if (keepTypeTransformer.isAssignableFrom(transformerClass)) {
                    illegalTransformer = false;
                    continue;
                }
            }

            if (illegalTransformer) {
                String errMsg = transformerClass.getName() +
                        " not in keepTypeTransformers, see keepTypeTransformers";
                throw new IllegalArgumentException(errMsg);
            }
        }
    }
}
