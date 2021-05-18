package com.syd.good.software.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syd.good.R
import kotlinx.android.synthetic.main.softwate_activity_info_collection.*

class InfoCollectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.softwate_activity_info_collection)
        init()
    }

    fun init() {
        bt_start.setOnClickListener {
            ProgressDialog.show(this, "收集信息", "正在收集周围Beacon信号")
        }
    }

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, InfoCollectionActivity::class.java)
            context.startActivity(intent)
        }
    }
}