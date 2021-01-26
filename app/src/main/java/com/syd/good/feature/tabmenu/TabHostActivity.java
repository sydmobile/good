package com.syd.good.feature.tabmenu;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTabHost;
import androidx.viewpager.widget.ViewPager;

import com.syd.good.R;
import com.syd.good.base.BaseActivity;
import com.syd.good.feature.fragment.fragment.FragmentOne;
import com.syd.good.feature.fragment.fragment.FragmentTwo;
import com.syd.good.feature.test.TestMainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * date: 2020/9/3 8:59
 * 使用 FragmentTabHost + ViewPager
 *
 * @author syd
 * @version 1.0
 */
public class TabHostActivity extends BaseActivity {
    private FragmentTabHost mFragmentTabHost;
    private LayoutInflater layoutInflater;
    private Class[] fragmentArray = {FragmentOne.class, FragmentTwo.class};
    private int[] imageViewArray = {R.drawable.tabmenu_tab_home_btn, R.drawable.tabmenu_tab_my_btn};
    private String[] textViewArray = {"首页", "分类"};
    private List<Fragment> list = new ArrayList<>();
    private ViewPager vp;

    @Override
    protected int layoutId() {
        return R.layout.tabmenu_activity_tabhost;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initView();
        initPage();

    }

    private void initView() {
        layoutInflater = LayoutInflater.from(this);
        vp = findViewById(R.id.vp);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mFragmentTabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // 实例化 FragmentTabHost 对象并绑定
        mFragmentTabHost = findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.vp);
        mFragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int position = mFragmentTabHost.getCurrentTab();
                vp.setCurrentItem(position);
            }
        });

        // 新建 Tabspec 选项卡并设置 Tab 菜单栏的内容和绑定对应的 Fragment
        for (int i = 0; i < textViewArray.length; i++) {

            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(textViewArray[i])
                    .setIndicator(getTabItemView(i));
            // 给 Tab 选项卡添加 文字和按钮，并绑定 Fragment
            mFragmentTabHost.addTab(tabSpec, fragmentArray[i], null);
            mFragmentTabHost.setTag(i);
            // 设置 Tab 被选中的时候颜色改变
//            mFragmentTabHost.getTabWidget().getChildAt(i)
//                    .setBackgroundResource(R.drawable.selector_tab_background);
        }

    }

    private void initPage() {
        FragmentOne fragmentOne = new FragmentOne();
        FragmentTwo fragmentTwo = new FragmentTwo();
        list.add(fragmentOne);
        list.add(fragmentTwo);
        vp.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));


    }

    private View getTabItemView(int i) {
        // 将 xml 布局转换为 view 对象
        View view = layoutInflater.inflate(R.layout.tabmenu_item_tab_item, null);
        // 利用 view 对象，找到布局中的组件，并设置内容，然后返回视图
        ImageView iv = view.findViewById(R.id.iv_tab);
        iv.setImageResource(imageViewArray[i]);
        TextView textView = view.findViewById(R.id.tv_tab);
        textView.setText(textViewArray[i]);
        return view;
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}
