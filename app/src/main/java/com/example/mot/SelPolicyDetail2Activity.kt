package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelPolicyDetail2Binding

class SelPolicyDetail2Activity : AppCompatActivity() {

    private var mBinding: ActivitySelPolicyDetail2Binding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelPolicyDetail2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mBinding!!.delBtn.setOnClickListener {
            finish()
        }

    }
}