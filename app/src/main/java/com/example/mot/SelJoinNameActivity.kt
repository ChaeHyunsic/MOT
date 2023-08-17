package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelJoinNameBinding

class SelJoinNameActivity : AppCompatActivity() {

    private var mBinding: ActivitySelJoinNameBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelJoinNameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (mBinding!!.accomNameTv.text.toString() != "") {
                    mBinding!!.dupliBtn.setBackgroundResource(R.drawable.btn_review)
                    mBinding!!.dupliBtn.setTextAppearance(R.style.splash_style)
                    mBinding!!.dupliBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11F)
                }
                else {
                    mBinding!!.dupliBtn.setBackgroundResource(R.drawable.dupli_check)
                    mBinding!!.dupliBtn.setTextAppearance(R.style.anno_style)
                    mBinding!!.dupliBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11F)
                }
            }
        }

        mBinding!!.accomNameTv.addTextChangedListener(textWatcher)

        mBinding!!.dupliBtn.setOnClickListener {
            if (!dupliCheck()) {
                return@setOnClickListener
            }
            else {
                Toast.makeText(this,"숙소명이 등록되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        mBinding!!.confirmBtn.setOnClickListener {
            if (mBinding!!.accomNameTv.error == null) {
                val intent = Intent(this, SelJoinLocationActivity::class.java)
                intent.putExtra("accom_name", mBinding!!.accomNameTv.text)

                startActivity(intent)
            }
            else {
                Toast.makeText(this,"숙소명이 등록되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun dupliCheck(): Boolean {
        val value: String = mBinding!!.accomNameTv.text.toString()
        val validCheck = true

        return if (value.isEmpty()) {
            mBinding!!.accomNameTv.error = "숙소명이 입력되지 않았습니다"
            false
        } else if (!validCheck) {
            mBinding!!.accomNameTv.error = "이미 등록되어 있는 숙소명입니다"
            false
        } else {
            mBinding!!.accomNameTv.error = null
            true
        }
    }
}