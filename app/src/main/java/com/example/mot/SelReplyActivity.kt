package com.example.mot

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelReplyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelReplyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelReplyBinding

    var commentId: Int? = null

    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.85.98:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(Service::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySelReplyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnSettingdone.setOnClickListener {

            service.replyWrite(/*TokenManager.getAuthToken(),*/ReplyWriteData(commentId!!.toInt(), binding.etReplyWriteBox.text.toString())).enqueue(object :
            Callback<ReplyData>{
                override fun onResponse(call: Call<ReplyData>, response: Response<ReplyData>) {
                    if(response.isSuccessful){
                        val result : ReplyData? = response.body()

                        if(result != null){
                            binding.etReplyWriteBox.setText(result.context)

                            finish()
                        }
                    }
                    else{
                        Toast.makeText(this@SelReplyActivity, "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ReplyData>, t: Throwable) {
                    Toast.makeText(this@SelReplyActivity, "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                }
            })
        }

        binding.btnReplyBack.setOnClickListener {
            finish()
        }
    }
}