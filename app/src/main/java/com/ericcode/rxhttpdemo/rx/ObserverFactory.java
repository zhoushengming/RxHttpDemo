package com.ericcode.rxhttpdemo.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * <pre>
 * Api.v2exRepo()
 *         .getHotTopics()
 *         .subscribe(ObserverFactory.create(
 *                 hotTopics ->
 *                         Logger.i("hot topic:" + hotTopics.size()),
 *                 e -> e.printStackTrace(),
 *                 () -> Logger.i("complete")));
 * </pre>
 */
public final class ObserverFactory {
    public static <T> Observer<T> create(Consumer<? super T> onNext) {
        return new InnerObserver<T>(onNext);
    }

    public static <T> Observer<T> create(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {
        return new InnerObserver<T>(onNext, onError);
    }

    public static <T> Observer<T> create(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
                                         Action onComplete) {
        return new InnerObserver<T>(onNext, onError, onComplete);
    }

    public static <T> Observer<T> create(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
                                         Action onComplete, Consumer<? super Disposable> onSubscribe) {
        return new InnerObserver<T>(onNext, onError, onComplete, onSubscribe);
    }

    private static class InnerObserver<T> implements Observer<T> {
        private Consumer<? super T> onNext;
        private Consumer<? super Throwable> onError;
        private Action onComplete;
        private Consumer<? super Disposable> onSubscribe;

        InnerObserver(Consumer<? super T> onNext) {
            this(onNext, null, null, null);
        }

        InnerObserver(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {
            this(onNext, onError, null, null);

        }

        InnerObserver(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
                      Action onComplete) {
            this(onNext, onError, onComplete, null);

        }

        InnerObserver(Consumer<? super T> onNext, Consumer<? super Throwable> onError,
                      Action onComplete, Consumer<? super Disposable> onSubscribe) {
            this.onNext = onNext;
            this.onError = onError;
            this.onComplete = onComplete;
            this.onSubscribe = onSubscribe;
        }

        @Override
        public void onSubscribe(Disposable d) {
            try {
                if (onSubscribe != null) {
                    onSubscribe.accept(d);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onNext(T t) {
            try {
                if (onNext != null) {
                    onNext.accept(t);
                }
            } catch (Exception e) {
                onError(e);
            }
        }

        @Override
        public void onError(Throwable e) {
            try {
                if (onError != null) {
                    onError.accept(e);
                } else {
                    e.printStackTrace();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void onComplete() {
            try {
                if (onComplete != null) {
                    onComplete.run();
                }
            } catch (Exception e) {
                onError(e);
            }
        }
    }

}
