package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.generateViewId
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mot.databinding.ActivitySellerPackageinfoDetailsBinding

class Seller_packageinfo_detailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySellerPackageinfoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySellerPackageinfoDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.adjustBtn.setOnClickListener {
            val intent = Intent(this, Seller_package_adjustActivity::class.java )

            startActivity(intent)
        }
        binding.adjustBtn2.setOnClickListener {
            val intent = Intent(this, Seller_basicinfo_strActivity::class.java )

            startActivity(intent)
        }

        binding.btnImaUpload.setOnClickListener {

            val imageView = ImageView(this)
            imageView.id = generateViewId()
            imageView.setPadding(130, 50, 0, 0)
            binding.pictureAddGl.addView(imageView)

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            activityResult.launch(intent)
        }

        binding.btn1Iv.setOnClickListener {
            binding.pict1Iv.setImageResource(0)
            binding.pict1Iv.visibility = View.GONE
            binding.btn1Iv.visibility = View.GONE
        }

        binding.btn2Iv.setOnClickListener {
            binding.pict2Iv.setImageResource(0)
            binding.pict2Iv.visibility = View.GONE
            binding.btn2Iv.visibility = View.GONE
        }

        binding.btn3Iv.setOnClickListener {
            binding.pict3Iv.setImageResource(0)
            binding.pict3Iv.visibility = View.GONE
            binding.btn3Iv.visibility = View.GONE
        }

        binding.btn4Iv.setOnClickListener {
            binding.pict4Iv.setImageResource(0)
            binding.pict4Iv.visibility = View.GONE
            binding.btn4Iv.visibility = View.GONE
        }

        binding.btn5Iv.setOnClickListener {
            binding.pict5Iv.setImageResource(0)
            binding.pict5Iv.visibility = View.GONE
            binding.btn5Iv.visibility = View.GONE
        }
        binding.btn1Iv.setOnClickListener {
            binding.pict1Iv.visibility = View.GONE
            binding.btn1Iv.visibility = View.GONE
        }
        binding.btn2Iv.setOnClickListener {
            binding.pict2Iv.visibility = View.GONE
            binding.btn2Iv.visibility = View.GONE
        }
        binding.btn3Iv.setOnClickListener {
            binding.pict3Iv.visibility = View.GONE
            binding.btn3Iv.visibility = View.GONE
        }
        binding.btn4Iv.setOnClickListener {
            binding.pict4Iv.visibility = View.GONE
            binding.btn4Iv.visibility = View.GONE
        }
        binding.btn5Iv.setOnClickListener {
            binding.pict5Iv.visibility = View.GONE
            binding.btn5Iv.visibility = View.GONE
        }



    }
    //결과 가져오기
    private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

        val imgs = listOf(binding.pict1Iv, binding.pict2Iv, binding.pict3Iv, binding.pict4Iv, binding.pict5Iv)
        val btns = listOf(binding.btn1Iv, binding.btn2Iv, binding.btn3Iv, binding.btn4Iv, binding.btn5Iv)

        //결과 코드 OK , 결가값 null 아니면
        if(it.resultCode == RESULT_OK && it.data != null){
            loop@for(img in imgs){
                for(btn in btns){
                    if (img.visibility == View.GONE && btn.visibility == View.GONE){
                        img.visibility = View.VISIBLE
                        btn.visibility = View.VISIBLE
                        //값 담기
                        val uri  = it.data!!.data
                        Glide.with(this)
                            .load(uri) //이미지
                            .into(img) //보여줄 위치
                        break@loop
                    }
                }
            }
        }
    }
}
