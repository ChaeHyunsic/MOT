package com.example.mot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelResResultBinding

class SelResResultActivity : AppCompatActivity() {

    private var mBinding: ActivitySelResResultBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelResResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.prevBtn.setOnClickListener {
            finish()
        }

        val start_date = intent.getStringExtra("start_date")
        val end_date = intent.getStringExtra("end_date")

        mBinding!!.chosenDate.setText(start_date + " ~ " + end_date)

        mBinding!!.resultCard.setOnClickListener {
            val intent = Intent(this, SelResResultDetailActivity::class.java)

            intent.putExtra("res_date", mBinding!!.chosenDate.text)

            startActivity(intent)
        }

    }
}