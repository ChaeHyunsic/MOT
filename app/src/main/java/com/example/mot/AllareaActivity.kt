package com.example.mot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityAllareaBinding

class AllareaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllareaBinding

    private var select_area : String? =null


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityAllareaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

//        var select_location : Button

        //액티비티 변경
        binding.btnAroundSelect.setOnClickListener {
            val intent = Intent(this, MyaroundActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        binding.btnMainSelect.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        binding.btnMypickSelect.setOnClickListener {
            val intent = Intent(this, MypickActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        binding.btnMypageSelect.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }


        binding.seoul.setOnClickListener {
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("seoul", "서울")

            startActivity(intent)

        }
        binding.busan.setOnClickListener {
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("busan", "부산")

            startActivity(intent)
        }
        binding.incheon.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("incheon", "인천")

            startActivity(intent)
        }
        binding.daejeon.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("daejeon", "대전")

            startActivity(intent)
        }
        binding.chungnam.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("chungnam", "충남/세종")

            startActivity(intent)
        }
        binding.chungbuk.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("chungbuk", "충북")

            startActivity(intent)
        }
        binding.jeonnam.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("jeonnam", "전남")

            startActivity(intent)
        }
        binding.gyeongnam.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("gyeongnam", "경남")

            startActivity(intent)
        }
        binding.gyeonggi.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("gyeonggi", "경기")

            startActivity(intent)
        }
        binding.daegu.setOnClickListener {
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("daegu", "대구/경산")

            startActivity(intent)
        }
        binding.gwangju.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("gwangju", "광주")

            startActivity(intent)
        }
        binding.ulsan.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("ulsan", "울산")

            startActivity(intent)
        }
        binding.gangwon.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("gangwon", "강원")

            startActivity(intent)
        }
        binding.jeonbuk.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("jeonbuk", "전북")

            startActivity(intent)
        }
        binding.gyeongbuk.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("gyeongbuk", "경북")

            startActivity(intent)
        }
        binding.jeju.setOnClickListener{
            val intent = Intent(this, AllareaItemActivity::class.java)
            intent.putExtra("jeju", "제주")

            startActivity(intent)
        }

    }
}



