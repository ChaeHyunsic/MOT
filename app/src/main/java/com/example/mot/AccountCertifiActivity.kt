package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityAccoutCertifiBinding

class AccountCertifiActivity: AppCompatActivity() {
    lateinit var binding: ActivityAccoutCertifiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccoutCertifiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIv.setOnClickListener {
            var certifi = binding.certifiInputEt.text.toString()
            var certifichk = intent.getStringExtra("certifi").toString()

            setResult(RESULT_OK, intent);
            finish()

//            if(certifi == certifichk){
//                setResult(RESULT_OK, intent);
//                finish()
//            }
        }

        binding.backIv.setOnClickListener {
            finish()
        }
    }
}