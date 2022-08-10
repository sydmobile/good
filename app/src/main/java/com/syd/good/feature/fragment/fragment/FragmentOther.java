package com.syd.good.feature.fragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.syd.good.R;
import com.syd.good.bean.entity.TestEntity;
import com.syd.good.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：首页
 * <p>
 * date: 2020/6/2 12:13
 *
 * @author syd
 * @version 1.0
 */
public class FragmentOther extends Fragment {
    private static final String TAG = "FragmentOther";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private TestEntity mTestEntity = new TestEntity();
    private List<String> mStrings;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        L.e(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e(TAG, "onCreate");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgament_fragment_home, container, false);
        ButterKnife.bind(this, view);
        L.e(TAG, "onCreateView");
        mTestEntity.setAge(18);
        mTestEntity.setLike("语文");
        mStrings = new ArrayList<>();
        mStrings.add("woshi 1");
        mStrings.add("woshi 2");
        mStrings.add("woshi 3");
        mStrings.add("woshi 4");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.e(TAG, "onViewCreated");
        toolbar.setTitle("其他");
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

    public TestEntity getTestEntity() {
        return mTestEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        mTestEntity = testEntity;
    }

    public List<String> getStrings() {
        return mStrings;
    }

    public void setStrings(List<String> strings) {
        mStrings = strings;
    }
}
