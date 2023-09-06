package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelAreaChooseBinding

class SelAreaChooseActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelAreaChooseBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySelAreaChooseBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var select_location: Button? = null

        val select_buttons = listOf(binding.btnSellSeoul, binding.btnSellBusan, binding.btnSellIncheon, binding.btnSellDaejeon, binding.btnSellChungnam,
                                binding.btnSellChungbuk, binding.btnSellJeonnam, binding.btnSellGyeongnam, binding.btnSellGyeonggi, binding.btnSellDeagu,
                                binding.btnSellGwangju, binding.btnSellUlsan, binding.btnSellGangwon, binding.btnSellJeonbuk, binding.btnSellGyeongbuk, binding.btnSellJeju)

        for (button in select_buttons) {
            button.setOnClickListener {
                if(select_location == null){
                    button.setBackgroundResource(R.drawable.btn_sell_area_select)
                }
                else {
                    select_location!!.setBackgroundResource(R.drawable.btn_sell_area_unselect)
                    button.setBackgroundResource(R.drawable.btn_sell_area_select)
                }
                select_location = button
            }
        }

        binding.btnSettingdone.setOnClickListener {
            if(select_location != null) {
                val intent = Intent(this, SelAddressWriteActivity::class.java)
                intent.putExtra("accom_name", intent.getStringExtra("accom_name"))
                intent.putExtra("accommodation_location", select_location!!.text.toString())

                startActivity(intent)
            }
            else {
                Toast.makeText(this,"지역이 선택되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}