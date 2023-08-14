package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelResResultDetailBinding

class SelResResultDetailActivity : AppCompatActivity() {

    private var mBinding: ActivitySelResResultDetailBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelResResultDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.delBtn.setOnClickListener {
            finish()
        }

        val res_date = intent.getStringExtra("res_date")

        mBinding!!.resDate.setText(res_date)

    }
}