package com.wacode.yuki.wakatimeex.UI.Auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.Button
import android.widget.Toast
import butterknife.bindView
import com.wacode.yuki.wakatimeex.MainActivity

import com.wacode.yuki.wakatimeex.R
import shimbaroid.wakalib.Authorization
import shimbaroid.wakalib.ScopeBuilder
import shimbaroid.wakalib.Scopes
import shimbaroid.wakalib.network.OnGetAccessTokenCallback
import wacode.yuki.wakatimeex.PrefUtils

class OAuthActivity : AppCompatActivity() {

    private val button:Button by bindView(R.id.button)

    private val scope = ScopeBuilder(Scopes.EMAIL)
    .addScope(Scopes.READ_LANGUAGE_USED)
    .addScope(Scopes.READ_LANGUAGE_USED)
    .addScope(Scopes.WRITE_LOGGED_TIME)
    .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setToolbar()

        button.setOnClickListener {
            Authorization.Builder(APPKEY, SECRETID, REQUESTTYPE, REDIRECTURI, scope).build().startAuthorization(this)
        }
    }

    private fun setToolbar(){
        val toolBar = findViewById(R.id.toolBar) as Toolbar
        toolBar.title = resources.getString(R.string.app_name)
        toolBar.logo = resources.getDrawable(R.mipmap.lologogo)
        setSupportActionBar(toolBar)
    }

    override fun onNewIntent(intent:Intent?){
        super.onNewIntent(intent)
        if(Authorization.isRedirectIntent(intent)) Authorization.getAuthorization().getAccessToken(this,intent,callback)
    }

    private val callback = OnGetAccessTokenCallback{ responseData ->
        PrefUtils.put(this,PREF_ACCESSTOKEN,responseData.access_token)
        Log.d("saruman",responseData.access_token)
        Toast.makeText(this,resources.getString(R.string.oauth_text_success),Toast.LENGTH_SHORT).show()
        finish()
    }

    companion object{
        private val TAG = this.javaClass.simpleName

        private val APPKEY = "dA-zVcce7iWSyGo6KD9iJ4NS"
        private val SECRETID = "sec_Ixdzu-mBFd7kD0HACHa-CF39zEw2m_f_Hak2evZ8kMMqBdV8znV68U-x5hxdwTXSYvV9qkd_zqHN3HSw"
        private val REDIRECTURI ="kaburazushi://wakalib"
        private val REQUESTTYPE = "code"

        val PREF_ACCESSTOKEN = "accessToken"
        val PREF_SECRETTOKEN = "secretToken"
    }
}
