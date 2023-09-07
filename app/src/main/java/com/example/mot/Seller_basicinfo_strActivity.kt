package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySellerBasicinfoStrBinding

class Seller_basicinfo_strActivity    : AppCompatActivity() {

        private var mBinding: ActivitySellerBasicinfoStrBinding? = null
        private val binding get() = mBinding!!

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            mBinding = ActivitySellerBasicinfoStrBinding.inflate(layoutInflater)
            setContentView(binding.root)

            mBinding!!.backBtn.setOnClickListener {
                finish()
            }

            mBinding!!.yellowBar.setOnClickListener {
                finish()
            }

        }
}