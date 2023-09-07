package com.example.mot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityPrivacyBinding

class PrivacyActivity    : AppCompatActivity() {

        private var mBinding: ActivityPrivacyBinding? = null
        private val binding get() = mBinding!!

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            mBinding = ActivityPrivacyBinding.inflate(layoutInflater)
            setContentView(binding.root)

            mBinding!!.xBtn.setOnClickListener {
                val intent =  Intent(this,PaymentActivity::class.java)
                startActivity(intent)
                finish()
            }




        }
}