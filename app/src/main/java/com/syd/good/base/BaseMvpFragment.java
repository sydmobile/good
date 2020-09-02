package com.syd.good.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 说明：$
 * <p>
 * date: 2020/6/22 16:52
 *
 * @author syd
 * @version 1.0
 */
@SuppressWarnings("unused")
public abstract class BaseMvpFragment<P extends IMVPContract.IBasePresenter> extends BaseFragment implements IMVPContract.IBaseView {
    @SuppressWarnings("WeakerAccess")
    protected P mPresenter;

    @SuppressWarnings("WeakerAccess")
    protected abstract P createPresenter();

    @SuppressWarnings("unchecked")
    @Override
    protected void init(View view, Bundle savedInstanceState) {
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
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

}
