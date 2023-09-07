package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mot.databinding.ActivityMypickBinding
import com.example.mot.databinding.PickListItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MypickActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMypickBinding

    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.85.98:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(Service::class.java)

    var id : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMypickBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
        binding.btnMypageSelect.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        service.heartInq(TokenManager.getAuthToken()).enqueue(object : Callback<List<HeartInqData>>{
            override fun onResponse(
                call: Call<List<HeartInqData>>,
                response: Response<List<HeartInqData>>
            ) {
                if(response.isSuccessful){
                    val results : List<HeartInqData>? = response.body()

                    if(results != null){
                        binding.itemList.removeAllViews()
                        for(result in results) {
                            val pickItembinding = PickListItemBinding.inflate(layoutInflater)

                            val url = result.photo

                            Glide.with(this@MypickActivity)
                                .load(url)
                                .into(pickItembinding.img)

                            pickItembinding.myreviewItemTitle.text = result.name
                            pickItembinding.rvGrade.rating = result.star.toFloat()
                            pickItembinding.tvTotalGrade.text = result.star.toString()
                            pickItembinding.tvCommentCount.text = result.commentCount.toString()

                            binding.itemList.addView(pickItembinding.root)
                        }
                    }
                }
                Toast.makeText(this@MypickActivity, "다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<List<HeartInqData>>, t: Throwable) {
                Toast.makeText(this@MypickActivity, "불러오기 실패. 다시 시도해주세요", Toast.LENGTH_SHORT).show()
            }
        })

        val pickCardview = PickListItemBinding.inflate(layoutInflater)
        pickCardview.imgHeart.setOnClickListener {
            service.heartDel(TokenManager.getAuthToken(),id!!.toInt()).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.isSuccessful){
                        Toast.makeText(this@MypickActivity, "찜 목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                    Toast.makeText(this@MypickActivity, "다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@MypickActivity, "다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}