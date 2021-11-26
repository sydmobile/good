package com.syd.good.feature.a_template;

import android.content.Intent;

import com.syd.good.databinding.CommonActivitySimpleBinding;
import com.syd.good.feature.a_common.base.SimpleActivity;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/22 17:24
 *     desc   : 简单的快速测试布局 TODO 描述功能
 *     version: 1.0
 * </pre>
 */
public class SimpleDemoActivity  extends SimpleActivity<CommonActivitySimpleBinding> {


    @Override
    protected void initBinding() {
        mBinding = CommonActivitySimpleBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initAnnotation() {
        // TODO 注释
        mBinding.tvAnnotation.setText("这是一个简单的快速集成页面");
    }

    @Override
    public void initView() {

    }


}
