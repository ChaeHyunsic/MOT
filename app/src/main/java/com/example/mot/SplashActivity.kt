package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private var mBinding: ActivitySplashBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Handler(Looper.getMainLooper()).postDelayed({

            val intent= Intent( this,LoginActivity::class.java)
            startActivity(intent)

            finish()

        }, 1000)
    }
}