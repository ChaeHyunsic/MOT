package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.mot.databinding.ActivityMyreviewBinding
import com.example.mot.databinding.MyreviewItemBinding
import com.example.mot.databinding.ReviewListItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyreviewBinding
    
    private var retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.85.98:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var service = retrofit.create(Service::class.java)

    var reserveId : String? = null
    var messageId : String? = null
    private var commentId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMyreviewBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 댓글 조회
        service.commentInq(TokenManager.getAuthToken()).enqueue(object : Callback<List<CommentInqData>> {
            override fun onResponse(call: Call<List<CommentInqData>>, response: Response<List<CommentInqData>>
            ) {
                if(response.isSuccessful){
                    val results : List<CommentInqData>? = response.body()

                    if(results != null){
                        for(result in results){
                            val InqReviewitemCard = MyreviewItemBinding.inflate(layoutInflater)

                            InqReviewitemCard.myreviewItemTitle.text = result.hotelName
                            InqReviewitemCard.tvMyreview.text = intent.getStringExtra("review_text")
                            InqReviewitemCard.myreviewRating.rating = result.star.toFloat()
                            InqReviewitemCard.myreviewUseRoom.text = result.packageName

                            binding.reviewItemLayout.addView(InqReviewitemCard.root)
                        }
                    }
                    else{
                        binding.reviewItemLayout.removeAllViews()
                    }
                }
            }

            override fun onFailure(call: Call<List<CommentInqData>>, t: Throwable) {
                Toast.makeText(this@MyreviewActivity, "잠시후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }

        })

        // Back Button click
        val btnBack : ImageButton = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        val reviewItem = ReviewListItemBinding.inflate(layoutInflater)

        // 리뷰 수정
        reviewItem.reviewEdit.setOnClickListener {
            val reviewText = findViewById<TextView>(R.id.tv_myreview).text.toString()
            val ratingBar = findViewById<RatingBar>(R.id.review_write_star).rating.toDouble()

            service.commentEdit(TokenManager.getAuthToken(),CommentWriteData(reviewText, ratingBar) ,commentId!!.toInt()).enqueue(object :
            Callback<CommentData> {
                override fun onResponse(call: Call<CommentData>, response: Response<CommentData>) {
                    if(response.isSuccessful) {
                        var result: CommentData? = response.body()

                        if(result != null){
                            val intent = Intent(this@MyreviewActivity, WriteReviewActivity::class.java)

                            startActivity(intent)
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<CommentData>, t: Throwable) {
                    Toast.makeText(this@MyreviewActivity, "다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }

            })
        }

        // 리뷰 삭제
        reviewItem.reviewDel.setOnClickListener {
            service.commentDel(TokenManager.getAuthToken(),commentId!!.toInt()).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.isSuccessful) {
                        val result = response.body()

                        if (result != null) {
                            findViewById<CardView>(R.id.myreview_cardview1).visibility = View.GONE
                            Toast.makeText(this@MyreviewActivity, "찜 목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show()

                        }
                    }
                    else{
                        Toast.makeText(this@MyreviewActivity, "다시 시도해 주시기 바랍니다.",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@MyreviewActivity, "다시 시도해 주시기 바랍니다.",Toast.LENGTH_SHORT).show()
                }
            })
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
    }
}