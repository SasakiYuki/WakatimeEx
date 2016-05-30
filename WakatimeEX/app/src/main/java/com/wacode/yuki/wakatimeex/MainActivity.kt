package com.wacode.yuki.wakatimeex

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.bindView
import com.wacode.yuki.wakatimeex.UI.Auth.AuthActivity
import wacode.yuki.newontapusha.Utils.PrefUtils

class MainActivity : AppCompatActivity() {
    private val toolBar:Toolbar by bindView(R.id.toolBar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        if(wantOAuthed()) {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setToolbar(){
        toolBar.title = resources.getString(R.string.app_name)
        toolBar.logo = resources.getDrawable(R.mipmap.lologogo)
        setSupportActionBar(toolBar)
    }

    private fun wantOAuthed() = PrefUtils[this, PREFKEY_WANTOAUTH,true]

    companion object{
        val PREFKEY_WANTOAUTH = "PrefKey_want_Oauthed"
    }

}
