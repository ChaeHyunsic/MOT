package com.example.mot


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelTrafficDetailBinding

class SelTrafficDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelTrafficDetailBinding
    private lateinit var sellocationfragment : SelLocationFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySelTrafficDetailBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 글자수 제한
        binding.etTrafficMoreBox.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (s?.length!! > 500) {
                    binding.etTrafficMoreBox.setError("최대 10자까지 입력 가능합니다.")
                } else {
                    binding.etTrafficMoreBox.setError(null)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }
        })

        binding.btnSettingdone.setOnClickListener {
            val trafficInfo : String = binding.etTrafficMoreBox.text.toString()
            val bundle : Bundle? = null

            bundle?.putString("trafficInfo", trafficInfo)

            sellocationfragment.arguments = bundle

            finish()
        }

        binding.btnTrafficMoreBack.setOnClickListener {
            finish()
        }
    }
}