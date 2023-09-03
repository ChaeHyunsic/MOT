package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityPersonBinding

class PersonActivity: AppCompatActivity() {

    lateinit var binding: ActivityPersonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var count:Int = 0

        binding.prevBtn.setOnClickListener {
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.choseBtn.setOnClickListener {
            val num = binding.numberBtn.text.toString()
            intent.putExtra("num", num)
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.plusBtn.setOnClickListener {
            count++
            binding.numberBtn.text = count.toString()

        }
    }
}