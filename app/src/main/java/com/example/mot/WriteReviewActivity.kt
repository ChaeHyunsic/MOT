package com.example.mot

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mot.databinding.ActivityWriteReviewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WriteReviewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityWriteReviewBinding
    lateinit var galleryAdapter: GalleryAdapter

    var imageList : ArrayList<Uri> = ArrayList()

    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.85.98:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(Service::class.java)

    var reserveId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityWriteReviewBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            finish()
        }

        // ratingBar
        binding.reviewWriteStar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            binding.tvRatingScore.text = "${rating}점"
        }

        // 리뷰 작성 50자 제한
        val maxLength = 50
        var textbound = binding.reviewWriteBox

        textbound.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))

        // GalleryAdater 초기화
        galleryAdapter = GalleryAdapter(imageList, this)

        // image recyclerView 설정
        binding.rvGalleryView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvGalleryView.adapter = galleryAdapter

        // 이미지 가져오기
        binding.btnReviewImg.setOnClickListener {
            // 갤러리 호출
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"

            // 멀티 선택 가능
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            activityResult.launch(intent)
        }

        // 입력 완료 버튼 클릭
        binding.btnWriteDone.setOnClickListener {

            service.commentWrite(CommentWriteData(binding.reviewWriteBox.text.toString(), binding.reviewWriteStar.rating.toDouble()), reserveId!!.toInt()).enqueue(object :
            Callback<CommentData> {
                override fun onResponse(call: Call<CommentData>, response: Response<CommentData>) {
                    if(response.isSuccessful){
                        var result : CommentData? = response.body()

                        if(result != null){

                            val text = binding.reviewWriteBox.text.toString()

                            val intent = Intent(this@WriteReviewActivity,MyreviewActivity::class.java)
                            intent.putExtra("review_text",text)
                            startActivity(intent)
                            finish()
                        }

                    }
                }

                override fun onFailure(call: Call<CommentData>, t: Throwable) {
                    Toast.makeText(this@WriteReviewActivity, "다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }


    // 결과 가져오기
    private var activityResult : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == RESULT_OK){

            // 이미지 멀티 선택은 clipData
            if(it.data!!.clipData != null){  // 멀티이미지
                // 선택한 이미지 수
                val count = it.data!!.clipData!!.itemCount

                if(count > 5){   //
                    Toast.makeText(this, "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_LONG).show();
                }
                else{
                    for(index in 0 until count) {
                        //이미지 담기
                        val imageUri = it.data!!.clipData!!.getItemAt(index).uri

                        //이미지 추가
                        imageList.add(imageUri!!)
                    }
                }
            }
            else {   // 싱글 이미지
                val imageUri = it.data!!.data
                imageList.add(imageUri!!)
            }
            galleryAdapter.notifyDataSetChanged()
        }
    }
}

