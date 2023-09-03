package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityInfoBinding

class InfoActivity:AppCompatActivity() {
    lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backIv.setOnClickListener {
            finish()
        }

        binding.btnIv.setOnClickListener {
            var text = binding.infoEt.text.toString()
            intent.putExtra("info", text)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}