package com.syd.good.feature.kotlin_practice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syd.good.R
import kotlinx.android.synthetic.main.common_activity.*

/**
 * <p>
 * date: 2021/2/3 10:22
 * @author syd
 * @version 1.0
 */
public class Test : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_activity)
        initView()
    }

    private fun initView() {
        tv_1.text = "xxx"
        tv_2.text = "2222"
        tv_1.text = "1"
        var s  = "xxxx"
        s.trim()
    }

}