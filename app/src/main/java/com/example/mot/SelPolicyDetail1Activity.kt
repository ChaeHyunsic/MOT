package com.example.mot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelPolicyDetail1Binding

class SelPolicyDetail1Activity : AppCompatActivity() {

    private var mBinding: ActivitySelPolicyDetail1Binding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelPolicyDetail1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mBinding!!.delBtn.setOnClickListener {
            finish()
        }

    }
}