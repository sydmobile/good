package com.syd.good.feature.fragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;
import com.syd.good.R;
import com.syd.good.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2020/6/2 12:13
 *
 * @author syd
 * @version 1.0
 */
public class FragmentOne extends Fragment {
    private static final String TAG = "FragmentOne";
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.bt_click)
    MaterialButton btClick;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        L.e(TAG, "onAttach");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgament_fragment_one, container, false);
        ButterKnife.bind(this, view);
        L.e(TAG, "onCreateView");
        return view;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.e(TAG, "onViewCreated");

        tvAdd.setOnClickListener((view1) -> {
            Log.e(TAG, "add");
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fl, new FragmentTwo());
            transaction.hide(FragmentOne.this);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        tvClose.setOnClickListener(view1 -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.hide(FragmentOne.this);
            transaction.commit();

        });

        // 与 Activity 通信
        btClick.setOnClickListener(view1 -> {
            if (getActivity() instanceof FOneBtnClickListener) {
                ((FOneBtnClickListener) getActivity()).onFOneBtnClick();
            }
        });


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e(TAG, "onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        L.e(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        L.e(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        L.e(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        L.e(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        L.e(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.e(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        L.e(TAG, "onDetach()");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        L.e(TAG, hidden + "");
    }

    public interface FOneBtnClickListener {
        void onFOneBtnClick();
    }

}
