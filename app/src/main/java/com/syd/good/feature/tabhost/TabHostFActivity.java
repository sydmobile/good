package com.syd.good.feature.tabhost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.syd.good.R;
import com.syd.good.feature.fragment.fragment.FragmentOne;
import com.syd.good.feature.fragment.fragment.FragmentTwo;

public class TabHostFActivity extends AppCompatActivity {
    private Class[] fragmentArray = {FragmentOne.class, FragmentTwo.class};
    private int[] imageViewArray = {R.drawable.tabmenu_tab_home_btn, R.drawable.tabmenu_tab_my_btn};
    private String[] textViewArray = {"首页", "分类"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhost_activity_tab_host_fragment);


        FragmentTabHost mFragmentTabHost = findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this,getSupportFragmentManager(),R.id.realcontent);


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

    private View getTabItemView(int i) {
        // 将 xml 布局转换为 view 对象
        View view = getLayoutInflater().inflate(R.layout.tabmenu_item_tab_item, null);
        // 利用 view 对象，找到布局中的组件，并设置内容，然后返回视图
        ImageView iv = view.findViewById(R.id.iv_tab);
        iv.setImageResource(imageViewArray[i]);
        TextView textView = view.findViewById(R.id.tv_tab);
        textView.setText(textViewArray[i]);
        return view;
    }
}