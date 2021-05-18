package com.syd.good.feature.officialdocument

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.syd.good.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.officialdocument_activity_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.officialdocument_resource, menu)
        return true
    }

    fun onGroupItemClick(item: MenuItem) {
        Log.e("xxx", item.title.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        Log.e("onOptionsItemSelected", item.title.toString())
        return super.onOptionsItemSelected(item)
    }
}