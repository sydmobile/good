package com.syd.good.software.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.syd.good.R
import kotlinx.android.synthetic.main.software_activity_login.*
/**
 * <p>
 * date: 2021/3/11 11:17
 * @author syd
 * @version 1.0
 */
open class LoginByPhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.software_activity_login)

        tv_send_code.setOnClickListener {
            if (et_phone_number.text.toString().isEmpty()) {
                Toast.makeText(this, "请输入手机号", Toast.LENGTH_LONG).show()
            } else {
                LoginByPasswordActivity.actionStart(this)
            }
        }

        tv_info_collection.setOnClickListener {
            InfoCollectionActivity.actionStart(this)
        }

    }
}