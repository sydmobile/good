package com.syd.good.feature.tabhost

import android.app.TabActivity
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TabHost
import com.syd.good.R
import com.syd.good.feature.tabmenu.TabHostActivity

class TabHostActivity : TabActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_host)

        init()
    }

    fun init(){
        // 获取该 activity 里面的 TabHost 组件
        val tabHost = tabHost
        val tab1 = tabHost.newTabSpec("tab1")
        tab1.setIndicator("表单1",getDrawable(R.drawable.ic_home))
        tab1.setContent(R.id.tab01)
        tabHost.addTab(tab1)

        val tab3 = tabHost.newTabSpec("tab3")
        tab3.setIndicator("表单3")
        tab3.setContent(R.id.tab03)
        tabHost.addTab(tab3)

        val tab2 = tabHost.newTabSpec("tab2")
        tab2.setIndicator("表单2")
        tab2.setContent(R.id.tab02)
        tabHost.addTab(tab2)


    }
}