package com.syd.good.feature.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.utils.L;

import butterknife.BindView;

/**
 * 说明：静态添加 Fragment
 * <p>
 * date: 2020/6/2 12:10
 *
 * @author syd
 * @version 1.0
 */
public class FragmentStaticActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.bt_home)
    MaterialButton btHome;
    @BindView(R.id.bt_my)
    MaterialButton btMy;
    @BindView(R.id.bt_other)
    MaterialButton btOther;
    @BindView(R.id.bt_add)
    MaterialButton btAdd;
    @BindView(R.id.bt_replace)
    MaterialButton btReplace;
    @BindView(R.id.ll)
    LinearLayout ll;


    @Override
    protected int layoutId() {
        return R.layout.fragment_activity_static;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
//        FragmentOne fragmentOne = new FragmentOne();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fl, fragmentOne)
//                .addToBackStack(null)
//                .commit();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fl, new FragmentTwo())
//                .addToBackStack(null)
//                .commit();
        initListener();
    }

    private void initListener() {

    }


    @Override
    public void onClick(View view) {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.e(TAG, "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.e(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.e(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        L.e(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        L.e(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e(TAG, "onDestroy()");
    }
}
