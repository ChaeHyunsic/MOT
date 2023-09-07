package com.example.mot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySellerPackageinfoAddpicBinding

class Seller_packageinfo_addpicActivity    : AppCompatActivity() {


    private var mBinding: ActivitySellerPackageinfoAddpicBinding? = null
        private val binding get() = mBinding!!

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            mBinding = ActivitySellerPackageinfoAddpicBinding.inflate(layoutInflater)
            setContentView(binding.root)

            mBinding!!.xBtn.setOnClickListener {
                finish()

            }
            mBinding!!.smallPackageStr7.setOnClickListener {
                val intent =  Intent(this,Seller_adjust_peopleActivity::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                finish()

            }
            mBinding!!.smallPackageStr8.setOnClickListener {
                val intent =  Intent(this,Seller_adjust_peopleActivity::class.java)
                getPersonCount.launch(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

            }


            mBinding!!.sellerAdjustBtn.setOnClickListener {
                mBinding!!.packageStr.text.toString()

                }
            mBinding!!.sellerAdjustBtn.setOnClickListener {
                mBinding!!.packageStr.text.toString()

            }
        }

    private val getPersonCount = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == Activity.RESULT_OK){
            binding.smallPackageStr8.text = result.data?.getStringExtra("personCnt") + "명"
        }
    }
}