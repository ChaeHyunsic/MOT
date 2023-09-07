package com.example.mot

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySellerPackageAdjustBinding

class Seller_package_adjustActivity : AppCompatActivity() {
    private var mBinding: ActivitySellerPackageAdjustBinding? = null
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySellerPackageAdjustBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.xBtn.setOnClickListener {
            finish()
        }
        mBinding!!.packageBtn.setOnClickListener {
            mBinding!!.sixOndolStr.visibility = View.VISIBLE
            mBinding!!.x3Btn.visibility = View.VISIBLE
        }
        mBinding!!.packageBtn2.setOnClickListener {
            mBinding!!.threeOndolStr2.visibility = View.VISIBLE
            mBinding!!.x3Btn2.visibility = View.VISIBLE
        }
        mBinding!!.packageBtn3.setOnClickListener {
            mBinding!!.smallOndolStr.visibility = View.VISIBLE
            mBinding!!.x3Btn3.visibility = View.VISIBLE
        }
        mBinding!!.packageBtn4.setOnClickListener {
            mBinding!!.bigOndolStr.visibility = View.VISIBLE
            mBinding!!.x3Btn4.visibility = View.VISIBLE
        }
        mBinding!!.x3Btn.setOnClickListener {
            mBinding!!.sixOndolStr.visibility = View.GONE
            mBinding!!.x3Btn.visibility = View.GONE
         }
        mBinding!!.x3Btn2.setOnClickListener {
            mBinding!!.threeOndolStr2.visibility = View.GONE
            mBinding!!.x3Btn2.visibility = View.GONE
        }
        mBinding!!.x3Btn3.setOnClickListener {
            mBinding!!.smallOndolStr.visibility = View.GONE
            mBinding!!.x3Btn3.visibility = View.GONE
        }
        mBinding!!.x3Btn4.setOnClickListener {
            mBinding!!.bigOndolStr.visibility = View.GONE
            mBinding!!.x3Btn4.visibility = View.GONE
        }
    }
}