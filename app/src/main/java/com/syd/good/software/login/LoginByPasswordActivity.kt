package com.syd.good.software.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.syd.good.R

class LoginByPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.software_activity_login_bypassword)
    }

    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, LoginByPasswordActivity::class.java)
            context.startActivity(intent)
        }
    }
}