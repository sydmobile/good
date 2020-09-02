package com.syd.good.rx;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 说明：RxHelper
 * <p>
 * date: 2020/6/23 10:30
 *
 * @author syd
 * @version 1.0
 */
public class RxHelper {

    /**
     * 重复操作，进行线程指定
     *
     * @param observable observable
     * @param <T>        T
     * @return 指定线程后的 observable
     */
    public static <T> Observable<T> observe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
