package com.example.mot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySellerAdjustPeopleBinding

class Seller_adjust_peopleActivity    : AppCompatActivity() {

        private var mBinding: ActivitySellerAdjustPeopleBinding? = null
        private val binding get() = mBinding!!

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            mBinding = ActivitySellerAdjustPeopleBinding.inflate(layoutInflater)
            setContentView(binding.root)

            mBinding!!.backBtn.setOnClickListener {
                val intent =  Intent(this, Seller_packageinfo_addpicActivity::class.java)
                startActivity(intent)
                finish()
            }
            mBinding!!.btnDone.setOnClickListener {
                val intent =  Intent(this,Seller_packageinfo_addpicActivity::class.java)
                startActivity(intent)
                finish()
            }
            mBinding!!.minusBtn.setOnClickListener {
                val curValue = mBinding!!.numberBtn.text.toString().toInt()
                if ( curValue > 1) {
                    mBinding!!.numberBtn.setText((curValue - 1).toString())
                }
            }

            mBinding!!.plusBtn.setOnClickListener {
                val curValue = mBinding!!.numberBtn.text.toString().toInt()
                if ( curValue < 999) {
                    mBinding!!.numberBtn.setText((curValue + 1).toString())
                }
            }

            // 선택 인원수 넘겨주기
            mBinding!!.btnDone.setOnClickListener {
                val countNum = mBinding!!.numberBtn.text.toString()
                val intent = Intent(this, Seller_packageinfo_addpicActivity::class.java)
                intent.putExtra("personCnt", countNum)
                setResult(RESULT_OK, intent)

                finish()
            }

        }
}