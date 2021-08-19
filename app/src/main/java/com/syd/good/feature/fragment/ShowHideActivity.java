package com.syd.good.feature.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.syd.good.R;
import com.syd.good.databinding.FragmentActivityShowHideBinding;
import com.syd.good.feature.fragment.fragment.FragmentHome;
import com.syd.good.feature.fragment.fragment.FragmentMy;
import com.syd.good.feature.fragment.fragment.FragmentThree;

/**
 * <pre>
 *     author : Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/8/9 17:16
 *     desc   : 用于验证懒加载
 *     version: 1.0
 * </pre>
 */
public class ShowHideActivity extends AppCompatActivity {

    FragmentActivityShowHideBinding mFragmentActivityShowHideBinding;

    FragmentHome mFragmentHome = new FragmentHome();
    FragmentMy mFragmentMy = new FragmentMy();
    FragmentThree mFragmentThree = new FragmentThree();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentActivityShowHideBinding = FragmentActivityShowHideBinding.inflate(getLayoutInflater());
        setContentView(mFragmentActivityShowHideBinding.getRoot());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl,mFragmentHome)
                .add(R.id.fl,mFragmentMy)
                .add(R.id.fl,mFragmentThree)
                .hide(mFragmentMy)
                .hide(mFragmentThree)
                .commit();

        // 设置点击监听

        mFragmentActivityShowHideBinding.btMy.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .show(mFragmentMy)
                    .hide(mFragmentThree)
                    .hide(mFragmentHome)
                    .commit();
        });

        mFragmentActivityShowHideBinding.btHome.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .show(mFragmentHome)
                    .hide(mFragmentThree)
                    .hide(mFragmentMy)
                    .commit();
        });

        mFragmentActivityShowHideBinding.btContacts.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .show(mFragmentThree)
                    .hide(mFragmentHome)
                    .hide(mFragmentMy)
                    .commit();
        });
    }

}