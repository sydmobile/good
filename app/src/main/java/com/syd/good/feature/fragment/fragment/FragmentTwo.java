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
public class FragmentTwo extends Fragment {
    private static final String TAG = "FragmentTwo";
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.tv_close)
    TextView tvClose;
    @BindView(R.id.tv_replace)
    MaterialButton tvReplace;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        L.e(TAG, "onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e(TAG, "onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        L.e(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.frgament_fragment_two, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvAdd.setOnClickListener((view1) -> {
            Log.e("add", "add");
            getFragmentManager().beginTransaction()
                    .add(R.id.fl, new FragmentThree())
                    .addToBackStack(null)
                    .commit();
        });
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.e("count", getFragmentManager().getBackStackEntryCount() + "");

                getFragmentManager().popBackStack();


            }
        });

        tvReplace.setOnClickListener(view1 -> {
            L.e(TAG,"transaction:"+getFragmentManager().beginTransaction());
            getFragmentManager().beginTransaction()
                    .replace(R.id.fl, new FragmentThree())
                    .addToBackStack(null)
                    .commit();
        });
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
}
