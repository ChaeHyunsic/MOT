package com.example.mot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySellerRoominfoBinding

class Seller_roominfoActivity    : AppCompatActivity() {

        private var mBinding: ActivitySellerRoominfoBinding? = null
        private val binding get() = mBinding!!

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            mBinding = ActivitySellerRoominfoBinding.inflate(layoutInflater)
            setContentView(binding.root)

            mBinding!!.xBtn.setOnClickListener {
                val intent =  Intent(this, Seller_tablayoutActivity::class.java)
                startActivity(intent)
                finish()
            }
            mBinding!!.smallPackageStr7.setOnClickListener {
                val intent =  Intent(this,Seller_adjust_peopleActivity::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

            }
            mBinding!!.smallPackageStr8.setOnClickListener {
                val intent =  Intent(this,Seller_adjust_peopleActivity::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

            }
        }
}