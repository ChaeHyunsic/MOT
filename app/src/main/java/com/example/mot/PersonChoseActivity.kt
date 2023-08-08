package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityPersonChoseBinding

class PersonChoseActivity : AppCompatActivity() {

    private var mBinding: ActivityPersonChoseBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityPersonChoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}