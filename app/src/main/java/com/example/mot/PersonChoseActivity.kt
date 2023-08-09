package com.example.mot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityPersonChoseBinding

class PersonChoseActivity : AppCompatActivity() {

    private var mBinding: ActivityPersonChoseBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityPersonChoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.prevBtn.setOnClickListener {
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

        mBinding!!.choseBtn.setOnClickListener {
            val intent = Intent()
            intent.putExtra("person", mBinding!!.numberBtn.text.toString())

            setResult(RESULT_OK, intent)
            finish()
        }

    }
}