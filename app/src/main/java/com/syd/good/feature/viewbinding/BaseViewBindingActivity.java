package com.syd.good.feature.viewbinding;

import android.os.Bundle;

import androidx.viewbinding.ViewBinding;

import com.syd.good.base.BaseActivity;

import java.lang.reflect.Type;

/**
 * <p>
 * date: 2021/2/22 15:31
 *
 * @author syd
 * @version 1.0
 */
public class BaseViewBindingActivity<B extends ViewBinding> extends BaseActivity {

    protected B binding;
    @Override
    protected int layoutId() {
        return 0;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
