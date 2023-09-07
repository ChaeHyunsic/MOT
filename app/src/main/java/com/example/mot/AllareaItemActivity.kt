package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mot.databinding.AccommodationItemBinding
import com.example.mot.databinding.ActivityAllareaItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AllareaItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllareaItemBinding

    private var keyword: String? = null

    private val hotelId: String? = null

    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.85.98:8080")  // 통신할 서버 주소
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(Service::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllareaItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        if (intent.getStringExtra("seoul") != null){
            keyword = intent.getStringExtra("seoul")
            binding.allareaItemTitle.text = intent.getStringExtra("seoul")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("busan") != null){
            keyword = intent.getStringExtra("busan")
            binding.allareaItemTitle.text = intent.getStringExtra("busan")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("incheon") != null){
            keyword = intent.getStringExtra("incheon")
            binding.allareaItemTitle.text = intent.getStringExtra("incheon")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("daejeon") != null){
            keyword = intent.getStringExtra("daejeon")
            binding.allareaItemTitle.text = intent.getStringExtra("daejeon")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("chungnam") != null){
            keyword = intent.getStringExtra("chungnam")
            binding.allareaItemTitle.text = intent.getStringExtra("chungnam")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("chungbuk") != null){
            keyword = intent.getStringExtra("chungbuk")
            binding.allareaItemTitle.text = intent.getStringExtra("chungbuk")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("jeonnam") != null){
            keyword = intent.getStringExtra("jeonnam")
            binding.allareaItemTitle.text = intent.getStringExtra("jeonnam")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("gyeongnam") != null){
            keyword = intent.getStringExtra("gyeongnam")
            binding.allareaItemTitle.text = intent.getStringExtra("gyeongnam")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("gyeonggi") != null){
            keyword = intent.getStringExtra("gyeonggi")
            binding.allareaItemTitle.text = intent.getStringExtra("gyeonggi")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("daegu") != null){
            keyword = intent.getStringExtra("daegu")
            binding.allareaItemTitle.text = intent.getStringExtra("daegu")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("gwangju") != null){
            keyword = intent.getStringExtra("gwangju")
            binding.allareaItemTitle.text = intent.getStringExtra("gwangju")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("ulsan") != null){
            keyword = intent.getStringExtra("ulsan")
            binding.allareaItemTitle.text = intent.getStringExtra("ulsan")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("gangwon") != null){
            keyword = intent.getStringExtra("gangwon")
            binding.allareaItemTitle.text = intent.getStringExtra("gangwon")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("gwangju") != null){
            keyword = intent.getStringExtra("gwangju")
            binding.allareaItemTitle.text = intent.getStringExtra("gwangju")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("gyeongbuk") != null){
            keyword = intent.getStringExtra("gyeongbuk")
            binding.allareaItemTitle.text = intent.getStringExtra("gyeongbuk")

            areaHotelList(keyword)
        }
        else if(intent.getStringExtra("jeju") != null){
            keyword = intent.getStringExtra("jeju")
            binding.allareaItemTitle.text = intent.getStringExtra("jeju")

            areaHotelList(keyword)
        }

        binding.btnFilter.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
        }

        binding.btnDate.setOnClickListener {
            val intent = Intent(this, DateChoseActivity::class.java)
            startActivity(intent)
        }

        binding.btnPersonCnt.setOnClickListener {
            val intent = Intent(this, PersonChoseActivity::class.java)
            startActivity(intent)
        }

         // 좋아요 생성
        var accommCardview = AccommodationItemBinding.inflate(layoutInflater)
        accommCardview.resPick.setOnClickListener {
            service.heartCreate(TokenManager.getAuthToken(),hotelId).enqueue(object : Callback<HeartCreateData>{
                override fun onResponse(
                    call: Call<HeartCreateData>,
                    response: Response<HeartCreateData>
                ) {
                    if (response.isSuccessful){
                        val result : HeartCreateData? = response.body()

                        if(result != null){

                            accommCardview.resPick.setImageResource(R.drawable.mypick)

                            Toast.makeText(this@AllareaItemActivity, "찜 목록에 추가하였습니다.\n 찜에서 확인해주세요", Toast.LENGTH_SHORT).show()
                        }
                        Toast.makeText(this@AllareaItemActivity, "목록 없음", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<HeartCreateData>, t: Throwable) {
                    Toast.makeText(this@AllareaItemActivity, "다시 시도해주세요", Toast.LENGTH_SHORT).show()
                }
            })
        }

        accommCardview.cardview1.setOnClickListener {
            // 해당 호텔 페이지로 이동
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    fun areaHotelList(keyword : String?) {
        service.areaSelect(keyword).enqueue(object :
            Callback<List<inq_accommodation>>{
            override fun onResponse(
                call: Call<List<inq_accommodation>>,
                response: Response<List<inq_accommodation>>
            ) {
                if (response.isSuccessful){
                    var results : List<inq_accommodation>? = response.body()

                    if(results != null){
                        for(result in results) {

                            val newCardViewBinding = AccommodationItemBinding.inflate(layoutInflater)
                            val url = result.photo

                            Glide.with(this@AllareaItemActivity)
                                .load(url)    // 불러올 이미지
                                .into(newCardViewBinding.itemImg)

                            newCardViewBinding.resItemTitle.text = result.name
                            newCardViewBinding.resRatingBar.rating = result.star.toFloat()
                            newCardViewBinding.ratingCount.text = result.star.toString()
                            newCardViewBinding.personPrice.text = result.price.toString() + " 원"
                            newCardViewBinding.totalPrice.text = result.price.toString() + " 원"

                            binding.resultItem.addView(newCardViewBinding.root)

                        }
                    }
                    else{
                        binding.resultItem.removeAllViews()
                    }
                }
                else{
                    Toast.makeText(this@AllareaItemActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<inq_accommodation>>, t: Throwable) {
                Toast.makeText(this@AllareaItemActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}