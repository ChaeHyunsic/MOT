package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityRoomBinding

class RoomActivity: AppCompatActivity() {

    lateinit var binding: ActivityRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.backIv.setOnClickListener {
            finish()
        }

        binding.btnIv.setOnClickListener {
            var text = binding.infoEt.text.toString()
            intent.putExtra("room_info", text)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}