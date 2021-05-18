package com.syd.good.feature.tabhost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TabHost
import androidx.fragment.app.FragmentTabHost
import com.syd.good.R
import com.syd.good.feature.viewpager.FragmentB

class TabHostFragmentActivity : AppCompatActivity() {

    val mImages = arrayOf(R.drawable.tabmenu_tab_home_btn,R.drawable.tabmenu_tab_my_btn,R.drawable.ic_message_selected)

    val mFragmentTags = arrayOf("one","two","three")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tabhost_activity_tab_host_fragment)

        val tabhost:FragmentTabHost = findViewById(android.R.id.tabhost)



        tabhost.setup(this,supportFragmentManager,R.id.realcontent)
        var tabSpec = tabhost.newTabSpec(mFragmentTags[0]).setIndicator(getImageView(0))
        tabhost.addTab(tabSpec,FragmentB::class.java,null)
//        for (i in mImages.indices){
//
//        }
    }

    fun getImageView(index:Int): View {
        val view:View = layoutInflater.inflate(R.layout.tabhost_tab_item,null)
        val iv = view.findViewById<ImageView>(R.id.iv)
        iv.setImageResource(mImages[index])
        return  view
    }
}