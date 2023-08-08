package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityFitterBinding

class FitterActivity : AppCompatActivity(){

    private var mBinding: ActivityFitterBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityFitterBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}