package com.example.mot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityPrivacy2Binding

class Privacy2Activity   : AppCompatActivity() {

    private var mBinding: ActivityPrivacy2Binding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityPrivacy2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.xBtn.setOnClickListener {
            val intent =  Intent(this,PaymentActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}