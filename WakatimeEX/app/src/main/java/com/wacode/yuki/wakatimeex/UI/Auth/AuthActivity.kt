package com.wacode.yuki.wakatimeex.UI.Auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import butterknife.bindView

import com.wacode.yuki.wakatimeex.R
import shimbaroid.wakalib.Authorization
import shimbaroid.wakalib.ScopeBuilder
import shimbaroid.wakalib.Scopes
import shimbaroid.wakalib.network.OnGetAccessTokenCallback
import wacode.yuki.newontapusha.Utils.PrefUtils

class AuthActivity : AppCompatActivity() {

    private val button:Button by bindView(R.id.button)

    private val scope = ScopeBuilder(Scopes.EMAIL)
    .addScope(Scopes.READ_LANGUAGE_USED)
    .addScope(Scopes.READ_LANGUAGE_USED)
    .addScope(Scopes.WRITE_LOGGED_TIME)
    .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        button.setOnClickListener {
            Authorization.Builder(APPKEY, SECRETID, REQUESTTYPE, REDIRECTURI, scope).build().startAuthorization(this)
        }
    }

    override fun onNewIntent(intent:Intent?){
        super.onNewIntent(intent)

        if(Authorization.isRedirectIntent(intent)) Authorization.getAuthorization().getAccessToken(this,intent,callback)
    }

    private val callback = OnGetAccessTokenCallback{ responseData ->
        Log.d("saru",responseData.access_token)
        PrefUtils.put(this,PREF_ACCESSTOKEN,responseData.access_token)
        finish()
    }

    companion object{
        private val TAG = this.javaClass.simpleName

        private val APPKEY = "PUlb8UrbhbgLp2cdoNsAmLEK"
        private val SECRETID = "sec_4QRgLueMtuZ2-FDucpmLAOAEhTDYqzFoWFLf0dUrf3JwbfBkiW7O-MDVmjOVRmHy6-E6PvSudaE-ifbF"
        private val REDIRECTURI ="kaburazushi://wakalib"
        private val REQUESTTYPE = "code"

        val PREF_ACCESSTOKEN = "accessToken"
        val PREF_SECRETTOKEN = "secretToken"
    }
}
