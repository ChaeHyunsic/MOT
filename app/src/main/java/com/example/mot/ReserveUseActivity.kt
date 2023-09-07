package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mot.databinding.ActivityReserveUseBinding
import com.example.mot.databinding.ReserveuseItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReserveUseActivity : AppCompatActivity() {

    private lateinit var binding : ActivityReserveUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityReserveUseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.85.98:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(Service::class.java)


        service.reserveInq(TokenManager.getAuthToken()).enqueue(object :
            Callback<List<ReserveInqData>>{
            override fun onResponse(
                call: Call<List<ReserveInqData>>,
                response: Response<List<ReserveInqData>>
            ) {
                if(response.isSuccessful){
                    val results : List<ReserveInqData>? = response.body()

                    if(results != null){
                        for (result in results) {
                            val newCardViewBinding = ReserveuseItemBinding.inflate(layoutInflater)

                            val url = result.photo

                            Glide.with(this@ReserveUseActivity)
                                .load(url) // 불러올 이미지 url
                                .into(newCardViewBinding.imgUse) // 이미지를 넣을 뷰

                            newCardViewBinding.button.text = result.name
                            newCardViewBinding.rbGrade.rating = result.star.toFloat()
                            newCardViewBinding.tvTotalGrade.text = result.star.toString()
                            newCardViewBinding.tvGradecount.text = "( " + result.commentCount.toString() + " )"
                            newCardViewBinding.tvRoom.text = result.packageInfo.toString() + " / " + result.roomInfo.toString()

                            binding.reserveListLayout.addView(newCardViewBinding.root)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<ReserveInqData>>, t: Throwable) {
                Toast.makeText(this@ReserveUseActivity, "불러오기 실패/n다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnMypageSelect.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        // activity 전환
        binding.btnAllareaSelect.setOnClickListener {
            val intent = Intent(this, AllareaActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        binding.btnAroundSelect.setOnClickListener {
            val intent = Intent(this, MyaroundActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        binding.btnMainSelect.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        binding.btnMypickSelect.setOnClickListener {
            val intent = Intent(this, MypickActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}