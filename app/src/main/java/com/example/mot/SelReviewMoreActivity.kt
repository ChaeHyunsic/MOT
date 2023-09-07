package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelReviewMoreBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelReviewMoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelReviewMoreBinding

    var commentId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySelReviewMoreBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.85.98:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(Service::class.java)

        // 후기 조회
        binding.userWriteReview.text = intent.getStringExtra("review")

        binding.btnReplyWrite.setOnClickListener {
            val intent = Intent(this, SelReplyActivity::class.java)

            startActivity(intent)

            Handler(Looper.getMainLooper()).postDelayed({
                binding.btnReplyWrite.isEnabled = false
            }, 1000)
        }

        binding.btnReviewBack.setOnClickListener {
            finish()
        }
    }
}