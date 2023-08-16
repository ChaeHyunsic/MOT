package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelPolicyBinding

class SelPolicyActivity : AppCompatActivity() {

    private var mBinding: ActivitySelPolicyBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelPolicyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var essCb_flag = false

        mBinding!!.essAllCb.setOnCheckedChangeListener { _, isChecked ->
            if (!essCb_flag) {
                mBinding!!.essCb1.isChecked = isChecked
                mBinding!!.essCb2.isChecked = isChecked
            }
            else {
                essCb_flag = false
            }
        }

        mBinding!!.essCb1.setOnCheckedChangeListener { _, isChecked ->
            if (mBinding!!.essAllCb.isChecked && !mBinding!!.essCb1.isChecked) {
                essCb_flag = true
            }
            mBinding!!.essAllCb.isChecked = mBinding!!.essCb1.isChecked && mBinding!!.essCb2.isChecked
        }

        mBinding!!.essCb2.setOnCheckedChangeListener { _, isChecked ->
            if (mBinding!!.essAllCb.isChecked && !mBinding!!.essCb2.isChecked) {
                essCb_flag = true
            }
            mBinding!!.essAllCb.isChecked = mBinding!!.essCb1.isChecked && mBinding!!.essCb2.isChecked
        }

        mBinding!!.essTv1.setOnClickListener {
            val intent = Intent(this, SelPolicyDetail1Activity::class.java)
            startActivity(intent)
        }

        mBinding!!.essTv2.setOnClickListener {
            val intent = Intent(this, SelPolicyDetail2Activity::class.java)
            startActivity(intent)
        }

        mBinding!!.confirmBtn.setOnClickListener {
            if (mBinding!!.essCb1.isChecked && mBinding!!.essCb2.isChecked) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this,"동의하지 않은 필수 약관이 있습니다.",Toast.LENGTH_SHORT).show()
            }
        }

    }
}