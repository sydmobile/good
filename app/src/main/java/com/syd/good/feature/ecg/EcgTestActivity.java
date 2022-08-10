package com.syd.good.feature.ecg;

import androidx.viewbinding.ViewBinding;

import com.syd.good.base.BaseActivity1;
import com.syd.good.databinding.EcgActivityTestBinding;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/25 14:29
 *     desc   : ecg 测试 局部刷新
 *     version: 1.0
 * </pre>
 */
public class EcgTestActivity extends BaseActivity1<EcgActivityTestBinding> {

    @Override
    protected void initBinding() {
        mBinding = EcgActivityTestBinding.inflate(getLayoutInflater());

    }

    @Override
    public void initAnnotation() {

    }

    @Override
    public void initView() {
        mBinding.bt.setOnClickListener(v -> {
            mBinding.testView.clearPartView(20,100);
        });

        mBinding.btOne.setOnClickListener(v -> {
            mBinding.testView.drawEcg();
        });
    }
}