package com.syd.good.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 说明：Base P 层
 * <p>
 * date: 2020/6/8 11:21
 *
 * @author syd
 * @version 1.0
 */

public abstract class BasePresenter<M, V> implements IMVPContract.IBasePresenter<M, V> {
    public final String TAG = getClass().getSimpleName();

    private CompositeDisposable mCompositeDisposable;

    private M mModel;

    private V mView;

    public BasePresenter() {
        this.mModel = createModel();
    }

    /**
     * 创建 Model 层
     *
     * @return Model
     */
    protected abstract M createModel();

    @Override
    public M getModel() {
        if (mModel == null) {
            throw new NullPointerException(String.format("%s cannot be null",
                    IMVPContract.IBaseModel.class.getSimpleName())
            );
        }
        return mModel;
    }

    @Override
    public V getView() {
        if (mView == null) {
            throw new NullPointerException(String.format("%s cannot be null",
                    IMVPContract.IBaseView.class.getSimpleName()));
        }
        return mView;
    }

    @Override
    public void attachView(V mView) {
        this.mView = mView;

    }

    @Override
    public void detachView() {
        // 保证 Activity 结束的时候取消
        unDispose();
        this.mView = null;
        this.mModel = null;
        this.mCompositeDisposable = null;
    }


    /**
     * 添加 disposable
     *
     * @param disposable disposable
     */
    @Override
    public void addDispose(Disposable disposable) {
        disposable.dispose();
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        // 将所有的 Disposable 放入集中处理
        mCompositeDisposable.add(disposable);
    }

    /**
     * 停止正在进行的任务
     */
    @Override
    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


}
