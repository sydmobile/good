package com.syd.good.base;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.syd.good.utils.ActivityManagerUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 说明：Activity 基类
 * <p>
 * date: 2020/6/8 10:45
 *
 * @author syd
 * @version 1.0
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {
    @SuppressWarnings("unused")
    public final String TAG = getClass().getSimpleName();
    /**
     * 用于 ButterKnife 解绑
     */
    private Unbinder mUnbinder;

    /**
     * 布局
     */
    protected abstract int layoutId();

    /**
     * 初始化
     */
    protected abstract void init(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Type type = getClass().getGenericSuperclass();
//        if (type instanceof ParameterizedType) {
//            @SuppressWarnings("unchecked")
//            Class<T> cls = (Class<T>) (((ParameterizedType) type).getActualTypeArguments()[0]);
//            try {
//                Method method = cls.getMethod("inflate", LayoutInflater.class);
//                binding = (T) method.invoke(null, getLayoutInflater());
//            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
        setContentView(layoutId());
        mUnbinder = ButterKnife.bind(this);

        // 入栈
        ActivityManagerUtils.getInstance().pushOneActivity(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
        init(savedInstanceState);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        this.mUnbinder = null;
        // 从栈中移除
        ActivityManagerUtils.getInstance().popOneActivity();
    }
}
