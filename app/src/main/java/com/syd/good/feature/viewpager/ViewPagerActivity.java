package com.syd.good.feature.viewpager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.utils.L;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * <p>
 * date: 2020/9/3 8:59
 * 普通通用的 Activity
 *
 * @author syd
 * @version 1.0
 */
public class ViewPagerActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Fragment> fragmentList;

    @Override
    protected int layoutId() {
        return R.layout.viewpager_activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                L.e(TAG, "onPageScrolled");

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}
