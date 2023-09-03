package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityRoomInfoBinding

class RoomInfoActivity: AppCompatActivity() {

    lateinit var binding: ActivityRoomInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backIv.setOnClickListener {
            finish()
        }

        binding.btnIv.setOnClickListener {
            var text = binding.infoEt.text.toString()
            intent.putExtra("basic_info", text)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}