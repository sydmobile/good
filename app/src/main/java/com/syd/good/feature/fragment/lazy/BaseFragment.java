package com.syd.good.feature.fragment.lazy;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

/**
 * <pre>
 *     author : Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/10 08:56
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BaseFragment extends Fragment {
    protected String TAG;

    {
        TAG = getClass().getSimpleName();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        Log.e(getClass().getSimpleName(), "onAttach");
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");
    }

}
