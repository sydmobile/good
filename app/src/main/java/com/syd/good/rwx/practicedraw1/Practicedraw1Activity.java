package com.syd.good.rwx.practicedraw1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.syd.good.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Practicedraw1Activity extends AppCompatActivity {

    private TabLayout mTabLayout;

    private ViewPager mViewPager;
    private List<PageModel> mPageModels = new ArrayList<>();

    {
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_color, R.string.title_draw_color, R.layout.ryg_a3customview_item_practice_color));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_circle, R.string.title_draw_circle, R.layout.ryg_a3customview_item_practice_circle));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_rect, R.string.title_draw_rect, R.layout.ryg_a3customview_item_practice_rect));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_point, R.string.title_draw_point, R.layout.ryg_a3customview_item_practice_point));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_oval, R.string.title_draw_oval, R.layout.ryg_a3customview_item_practice_oval));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_line, R.string.title_draw_line, R.layout.ryg_a3customview_item_practice_line));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_round_rect, R.string.title_draw_round_rect, R.layout.ryg_a3customview_item_practice_round_rect));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_arc, R.string.title_draw_arc, R.layout.ryg_a3customview_item_practice_arc));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_path, R.string.title_draw_path, R.layout.ryg_a3customview_item_practice_path));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_path, R.string.title_draw_bitmap, R.layout.ryg_a3customview_item_practice_img));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_histogram, R.string.title_draw_histogram, R.layout.ryg_a3customview_item_practice_histogram));
        mPageModels.add(new PageModel(R.layout.ryg_a3customview_item_sample_pie_chart, R.string.title_draw_pie_chart, R.layout.ryg_a3customview_item_practice_pie_chart));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rwx_practicedraw1_activity_practicedraw1);
        mViewPager = findViewById(R.id.vp);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @NotNull
            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = mPageModels.get(position);

                return PageFragment.newInstance(pageModel.sampleLayoutRes,pageModel.practiceLayoutRes);
            }

            @Override
            public int getCount() {
                return mPageModels.size();
            }

            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return getString(mPageModels.get(position).titleRes);
            }

        });
        mTabLayout = findViewById(R.id.tbl);
        mTabLayout.setupWithViewPager(mViewPager);
    }



    private class PageModel{
        int sampleLayoutRes;
        int titleRes;
        int practiceLayoutRes;

        PageModel(int sampleLayoutRes,int titleRes,int practiceLayoutRes){
            this.sampleLayoutRes = sampleLayoutRes;
            this.titleRes = titleRes;
            this.practiceLayoutRes = practiceLayoutRes;
        }
    }
}