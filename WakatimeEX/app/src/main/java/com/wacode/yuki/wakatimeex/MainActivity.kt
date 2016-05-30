package com.wacode.yuki.wakatimeex

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.bindView
import com.wacode.yuki.wakatimeex.UI.Auth.AuthActivity
import shimbaroid.wakalib.ScopeBuilder
import shimbaroid.wakalib.Scopes

class MainActivity : AppCompatActivity() {
    private val toolBar:Toolbar by bindView(R.id.toolBar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        val intent = Intent(this,AuthActivity::class.java)
        startActivity(intent)
    }

    private fun setToolbar(){
        toolBar.title = " WakaTime"
        toolBar.logo = resources.getDrawable(R.mipmap.lologogo)
        setSupportActionBar(toolBar)
    }

}
