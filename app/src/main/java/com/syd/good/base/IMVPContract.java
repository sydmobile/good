package com.syd.good.base;

import io.reactivex.disposables.Disposable;

/**
 * 说明：MVP 合约
 * <p>
 * date: 2020/6/8 11:00
 *
 * @author syd
 * @version 1.0
 */
public interface IMVPContract {

    interface IBaseModel {

    }

    interface IBaseView<P> {

        /**
         * Presenter
         */
        P getPresenter();

        /**
         * 显示加载
         */
        void showLoading();

        /**
         * 隐藏加载
         */
        void hideLoading();

        /**
         * 显示信息
         */
        void showMsg(String msg);

        /**
         * 出现错误不合理会提示
         * 比如：请求的数据 success 为 false
         *
         * @param msg 信息
         */
        void showErrorMsg(String msg);

        /**
         * 显示异常信息，程序出现异常错误会调用
         *
         * @param exceptionMsg 异常信息
         */
        void showExceptionMsg(String exceptionMsg);
    }

    interface IBasePresenter<M, V> {

        /**
         * 绑定 View
         *
         * @param mView view层
         */
        void attachView(V mView);

        /**
         * 解除绑定
         */
        void detachView();

        M getModel();

        V getView();

        void addDispose(Disposable disposable);

        void unDispose();
    }

}
