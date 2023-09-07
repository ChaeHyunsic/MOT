package com.example.mot

import android.os.Bundle
import android.text.InputFilter
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelTrafficDetailBinding
class SelTrafficDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelTrafficDetailBinding
    private lateinit var sellocationfragment : SelLocationFragment

    var bundle : Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySelTrafficDetailBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 글자수 제한
        val maxLength = 500
        var textbound = binding.etTrafficMoreBox

        textbound.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))

        val trafficInfo = binding.etTrafficMoreBox.text.toString()
        binding.btnSettingdone.setOnClickListener {

            bundle?.putString("trafficInfo", trafficInfo)

            sellocationfragment = SelLocationFragment()
            sellocationfragment.arguments = bundle

            finish()
        }

        binding.btnTrafficMoreBack.setOnClickListener {
            finish()
        }
    }
}