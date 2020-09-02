package com.syd.good.rx;

import android.text.TextUtils;

import com.syd.good.base.BaseEntity;
import com.syd.good.base.IMVPContract;
import com.syd.good.constant.AppConstant;

import io.reactivex.observers.ResourceObserver;

/**
 * 说明：BaseObserver
 * <p>
 * date: 2020/6/8 17:53
 *
 * @author syd
 * @version 1.0
 */
public abstract class BaseObserver<T extends BaseEntity> extends ResourceObserver<T> {
    private IMVPContract.IBaseView mView;
    private boolean mIsShowLoading = true;

    protected BaseObserver(IMVPContract.IBaseView view) {
        this.mView = view;
    }

    @SuppressWarnings("unused")
    public BaseObserver(IMVPContract.IBaseView view, boolean bShowLoading) {
        this.mView = view;
        this.mIsShowLoading = bShowLoading;
    }

    /**
     * 成功的回调
     */
    protected abstract void onSuccess(T t);

    /**
     * 服务器返回数据不对
     */
    protected abstract void disposeErrorData(T t);

    /**
     * 处理异常
     */
    protected abstract void disposeException(Throwable t);

    @Override
    protected void onStart() {
        super.onStart();
        if (!mIsShowLoading) {
            return;
        }
        if (mView != null) {
            mView.showLoading();
        }
    }

    @Override
    public void onNext(T t) {
        if (mView != null) {
            mView.hideLoading();
        }
        if (t.isSuccess()) {
            onSuccess(t);
            return;
        }
        disposeErrorData(t);

        if (TextUtils.isEmpty(t.getMessage())) {
            return;
        }

        if (mView != null) {
            mView.showErrorMsg(t.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        if (isDisposed()) {
            dispose();
        }
        disposeException(e);
        if (mView != null) {
            mView.hideLoading();
            mView.showExceptionMsg(e == null ? AppConstant.HTTP.ERROR : e.getMessage() + " ");
            mView = null;
        }

    }

    @Override
    public void onComplete() {
        if (mView != null) {
            mView.hideLoading();
            mView = null;
        }
        if (!isDisposed()) {

            dispose();
        }
    }


}
