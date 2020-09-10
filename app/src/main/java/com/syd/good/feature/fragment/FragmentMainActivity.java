package com.syd.good.feature.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;
import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.feature.fragment.fragment.FragmentHome;
import com.syd.good.feature.fragment.fragment.FragmentMy;
import com.syd.good.feature.fragment.fragment.FragmentOne;
import com.syd.good.feature.fragment.fragment.FragmentOther;
import com.syd.good.feature.fragment.fragment.FragmentThree;
import com.syd.good.feature.fragment.fragment.FragmentTwo;
import com.syd.good.utils.L;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：$
 * <p>
 * date: 2020/6/2 12:10
 *
 * @author syd
 * @version 1.0
 */
public class FragmentMainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.bt_add)
    Button btAdd;
    @BindView(R.id.bt_replace)
    Button btReplace;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.bt_home)
    MaterialButton btHome;
    @BindView(R.id.bt_my)
    MaterialButton btMy;
    @BindView(R.id.bt_other)
    MaterialButton btOther;
    FragmentHome fragmentHome;
    FragmentMy fragmentMy;
    FragmentOther fragmentOther;
    @BindView(R.id.bt_add1)
    MaterialButton btAdd1;

    @Override
    protected int layoutId() {
        return R.layout.fragment_activity_main;
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
        btHome.setOnClickListener(this);
        btMy.setOnClickListener(this);
        btOther.setOnClickListener(this);

        //  全Fragment 和 一半的 Fragment
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                L.e(TAG,"transaction:"+getSupportFragmentManager().beginTransaction());
                L.e(TAG,"transaction:"+getSupportFragmentManager().beginTransaction());
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fl, new FragmentOne())
                        .add(R.id.fl, new FragmentTwo())
//                        .addToBackStack(null)
                        .commit();
            }
        });

        // 两个 全 Fragment
        btAdd1.setOnClickListener(view -> {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl, new FragmentOne())
                    .add(R.id.fl, new FragmentThree())
                    .commit();
        });
    }


    @Override
    public void onClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.bt_home:
                if (fragmentHome == null) {
                    L.e(TAG, "click:fragmentHome = null");
                    fragmentHome = new FragmentHome();
                }
                transaction.replace(R.id.fl, fragmentHome);
                break;
            case R.id.bt_my:
                if (fragmentMy == null) {
                    fragmentMy = new FragmentMy();
                }
                transaction.replace(R.id.fl, fragmentMy);
                break;
            case R.id.bt_other:
                if (fragmentOther == null) {
                    fragmentOther = new FragmentOther();
                }
                transaction.replace(R.id.fl, fragmentOther);
            default:
        }
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
