package com.wacode.yuki.wakatimeex

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import butterknife.bindView
import com.wacode.yuki.wakatimeex.UI.Auth.OAuthActivity
import com.wacode.yuki.wakatimeex.UI.Profile.ProfileMainActivity
import wacode.yuki.wakatimeex.PrefUtils

class MainActivity : AppCompatActivity() {
    private val toolBar:Toolbar by bindView(R.id.toolBar)
    private val drawerLayout:DrawerLayout by bindView(R.id.drawerLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        initToolBar()
        if(wantOAuthed()) {
            val intent = Intent(this, OAuthActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setToolbar(){
        toolBar.title = resources.getString(R.string.app_name)
        toolBar.logo = resources.getDrawable(R.mipmap.lologogo)
        setSupportActionBar(toolBar)
    }

    private fun wantOAuthed() = PrefUtils[this, PREFKEY_WANTOAUTH,true]

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0,MENU_SETTING,0,resources.getString(R.string.main_profile))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            MENU_SETTING -> intentProfile()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun intentProfile(){
        val intent = Intent(this,ProfileMainActivity::class.java)
        startActivity(intent)
    }

    private fun initToolBar(){
        setSupportActionBar(toolBar)
        val drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.drawer_open, R.string.drawer_close)
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()
        drawerLayout.setDrawerListener(drawerToggle)
    }

    companion object{
        val PREFKEY_WANTOAUTH = "PrefKey_want_Oauthed"
        private  val MENU_SETTING = 0
    }

}
