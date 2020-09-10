package com.syd.good.feature.mdc;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.syd.good.R;
import com.syd.good.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * date: 2020/9/8 11:10
 *
 * @author syd
 * @version 1.0
 */
public class MDCTabLayoutActivity extends BaseActivity {
    @BindView(R.id.tbl)
    TabLayout tbl;
    @BindView(R.id.vp)
    ViewPager vp;
    List<Fragment> list = new ArrayList<>();
    String[] titles = new String[]{"推荐", "关注", "天气", "新闻", "娱乐", "经济"};

    @Override
    protected int layoutId() {
        return R.layout.mdc_activity_tablayout;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        list.add(MyFragment.newInstance(0));
        list.add(MyFragment.newInstance(1));
        list.add(MyFragment.newInstance(2));
        list.add(MyFragment.newInstance(3));
        list.add(MyFragment.newInstance(4));
        list.add(MyFragment.newInstance(5));

        vp.setAdapter(new MyViewPageAdapter(getSupportFragmentManager(), 1));
        tbl.setupWithViewPager(vp);

        for (String t : titles) {
            tbl.addTab(tbl.newTab().setText(t));
        }
        tbl.setTabMode(TabLayout.MODE_SCROLLABLE);
        tbl.setTabTextColors(Color.BLACK, Color.RED);
        tbl.setSelectedTabIndicatorColor(Color.BLUE);
        tbl.setTabIndicatorFullWidth(false);
        tbl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public static class MyFragment extends Fragment {
        String[] titles = new String[]{"推荐", "关注", "天气", "新闻", "娱乐", "经济"};

        public static MyFragment newInstance(int position) {
            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.mdc_fragment_item, container, false);
            int position = getArguments().getInt("position");
            TextView tv = view.findViewById(R.id.tv);
            tv.setText(titles[position]);
            return view;
        }
    }

    class MyViewPageAdapter extends FragmentPagerAdapter {
        String[] titles = new String[]{"推荐", "关注", "天气", "新闻", "娱乐", "经济"};

        public MyViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
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
