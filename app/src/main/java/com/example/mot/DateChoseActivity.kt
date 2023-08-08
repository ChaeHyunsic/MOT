package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityDateChoseBinding

class DateChoseActivity : AppCompatActivity() {

    private var mBinding: ActivityDateChoseBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityDateChoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}