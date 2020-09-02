package com.syd.good.base;

import android.os.Bundle;
import android.widget.Toast;

/**
 * 说明：使用 MVP模式的Activity基类
 * <p>
 * date: 2020/6/8 10:55
 *
 * @author syd
 * @version 1.0
 */
public abstract class BaseMvpActivity<P extends IMVPContract.IBasePresenter> extends BaseActivity implements IMVPContract.IBaseView<P> {
    protected P mPresenter;

    /** 创建 */
    protected abstract P createPresenter();

    @SuppressWarnings("unchecked")
    @Override
    protected void init(Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void showErrorMsg(String msg) {
        showMsg("tip:" + msg);
    }

    @Override
    public void showExceptionMsg(String errorMsg) {
        showMsg("exception:" + errorMsg);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        this.mPresenter = null;
    }
}
