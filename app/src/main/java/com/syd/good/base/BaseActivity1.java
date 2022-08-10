package com.syd.good.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/11/22 17:24
 *     desc   : 简单的快速测试布局
 *     version: 1.0
 * </pre>
 */
public abstract class BaseActivity1<T extends ViewBinding> extends AppCompatActivity {
    protected T mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setContentView(mBinding.getRoot());
        init(savedInstanceState);
    }

    /**
     * 初始化 ViewBinding
     */
    protected abstract void initBinding();

    /**
     * 初始化 操作
     * @param bundle bundle
     */
    protected void init(Bundle bundle){
        initAnnotation();
        initView();
    }

    /**
     * 注释内容
     */
    public abstract void initAnnotation();

    /**
     * 初始化 View
     */
    public abstract void initView();
}
