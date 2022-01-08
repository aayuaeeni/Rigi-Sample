package com.raju.rigi_sample.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.raju.rigi_sample.application.RigiApp
import com.raju.rigi_sample.chat.MainActivity
import com.raju.rigi_sample.databinding.ActivitySplashBinding
import com.raju.rigi_sample.login.LoginActivity
import com.raju.rigi_sample.utils.loadImageFromUrl

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()
        openApp()
    }

    private fun setViews() {
        val imageUrl = "https://app.rigi.club/img/4_BG_Transparent_Uppercase.png"
        binding.ivLogo.loadImageFromUrl(imageUrl)
    }

    private fun openApp() {
        Handler().postDelayed({
            val app: RigiApp = application as RigiApp
            val pref = app.getPref()
            if (pref?.getIsLogin() == true){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 2000)
    }
}