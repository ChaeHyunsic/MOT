package com.example.mot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelJoinAddressBinding

class SelJoinAddressActivity : AppCompatActivity() {

    private var mBinding: ActivitySelJoinAddressBinding? = null
    private val binding get() = mBinding!!

    private var accom_address: String? = null

    private val getResultFitter = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            accom_address = data?.getStringExtra("accom_address")
            mBinding!!.addressTv.setText(accom_address)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelJoinAddressBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mBinding!!.chosenLocationTv.setText(intent.getStringExtra("accom_location"))

        mBinding!!.addressTv.setOnClickListener {
            val intent = Intent(this, SelJoinAddressDetailActivity::class.java)

            getResultFitter.launch(intent)
        }

        mBinding!!.confirmBtn.setOnClickListener {
            if (mBinding!!.addressTv != null && mBinding!!.addressDetailTv != null) {
                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            }
            else {
                Toast.makeText(this,"입력되지 않은 주소가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}