package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelLocationDetailBinding

class SelLocationDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelLocationDetailBinding

    private var bundle : Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySelLocationDetailBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLocationDetailBack.setOnClickListener {
            finish()
        }

        binding.btnSettingdone.setOnClickListener {
            val text = binding.etLocationDetailBox.text.toString()

            bundle?.putString("locationInfo",text)

            val selLocationFragment = SelLocationFragment()
            selLocationFragment.arguments = bundle

            finish()
//            supportFragmentManager
//                .beginTransaction()
//               .replace(R.id.tab_frameLayout, SelLocationFragment()).commit()
        }
    }
}