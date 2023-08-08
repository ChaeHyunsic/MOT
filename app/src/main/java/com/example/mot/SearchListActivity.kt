package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySearchListBinding

class SearchListActivity : AppCompatActivity() {

    private var mBinding: ActivitySearchListBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySearchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}