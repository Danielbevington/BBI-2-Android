package com.bbi.bizbulls

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bbi.bizbulls.databinding.ActivitySplashBinding
import com.bbi.bizbulls.sharedpref.SharedPrefsManager

class SplashScreenActivity : AppCompatActivity() {
    private val sharedPrefsHelper by lazy { SharedPrefsManager(this@SplashScreenActivity) }
    var binding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        //Set your theme before super.onCreate() to replace your previous theme of Manifest
        setTheme(R.style.Launcher)
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        checkLogin()
    }

    private fun checkLogin() {
        if (sharedPrefsHelper.isLogin) {
            val i = Intent(this@SplashScreenActivity, DashboardActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        } else {
            val i = Intent(this@SplashScreenActivity, SignupActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
        finish()
    }
}