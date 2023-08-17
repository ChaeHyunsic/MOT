package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelJoinLocationBinding

class SelJoinLocationActivity : AppCompatActivity() {

    private var mBinding: ActivitySelJoinLocationBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelJoinLocationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var chosen_location: Button? = null

        val buttons = listOf(mBinding!!.locBtn, mBinding!!.locBtn2, mBinding!!.locBtn3, mBinding!!.locBtn4, mBinding!!.locBtn5, mBinding!!.locBtn6, mBinding!!.locBtn7, mBinding!!.locBtn8,
            mBinding!!.locBtn9, mBinding!!.locBtn10, mBinding!!.locBtn11, mBinding!!.locBtn12, mBinding!!.locBtn13, mBinding!!.locBtn14, mBinding!!.locBtn15, mBinding!!.locBtn16)

        for (button in buttons) {
            button.setOnClickListener {
                if (chosen_location == null) {
                    button.setBackgroundResource(R.drawable.btn_review)
                }
                else {
                    chosen_location!!.setBackgroundResource(R.drawable.location_btn_background)
                    button.setBackgroundResource(R.drawable.btn_review)
                }
                chosen_location = button
            }
        }

        mBinding!!.confirmBtn.setOnClickListener {
            if (chosen_location != null) {
                val intent = Intent(this, SelJoinAddressActivity::class.java)
                intent.putExtra("accom_name", intent.getStringExtra("accom_name"))
                intent.putExtra("accom_location", chosen_location!!.text.toString())

                startActivity(intent)
            }
            else {
                Toast.makeText(this,"지역이 선택되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}