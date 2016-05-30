package com.wacode.yuki.wakatimeex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.bindView

class MainActivity : AppCompatActivity() {
    private val toolBar:Toolbar by bindView(R.id.toolBar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
    }

    private fun setToolbar(){
        toolBar.title = " WakaTime"
        toolBar.logo = resources.getDrawable(R.mipmap.lologogo)
        setSupportActionBar(toolBar)
    }
}
