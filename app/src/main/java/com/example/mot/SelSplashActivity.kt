package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelSplashBinding

class SelSplashActivity : AppCompatActivity() {

    private var mBinding: ActivitySelSplashBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelSplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Handler(Looper.getMainLooper()).postDelayed({

            val intent= Intent( this,SelPolicyActivity::class.java)
            startActivity(intent)

            finish()

        }, 1000)
    }
}