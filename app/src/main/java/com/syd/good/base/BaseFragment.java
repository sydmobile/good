package com.syd.good.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * 说明：BaseFragment
 * <p>
 * date: 2020/6/22 16:43
 *
 * @author syd
 * @version 1.0
 */
public abstract class BaseFragment extends Fragment {
    @SuppressWarnings("unused")
    public final String TAG = getClass().getSimpleName();

    /** 布局 */
    @SuppressWarnings("WeakerAccess")
    protected abstract int layoutId();

    /** 初始化 */
    protected abstract void init(View view, Bundle savedInstanceState);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view, savedInstanceState);
    }

}
