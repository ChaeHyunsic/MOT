package com.example.mot


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelAddressWriteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelAddressWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelAddressWriteBinding

    private var bundle : Bundle? = null

    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.85.98:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(Service::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySelAddressWriteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val accommodation_name = intent.getStringExtra("accommadation_name")
        val accommodation_location = intent.getStringExtra("accommodation_location")

        binding.selectedArea.setText(accommodation_location)

        binding.btnSettingdone.setOnClickListener {
            if (binding.etAddressWrite != null && binding.etAdrDetailWrite != null) {
                service.hotel_add(/*TokenManager.getAuthToken(),*/HotelRequestData(accommodation_name.toString(), accommodation_location.toString(),
                    binding.etAddressWrite.text.toString(), binding.etAdrDetailWrite.text.toString())
                ).enqueue(object :
                    Callback<HotelResponseData> {

                    // 변경할 주소
                    val change_address = accommodation_location + binding.etAddressWrite.text.toString() + binding.etAdrDetailWrite.text.toString()

                    override fun onResponse(
                        call: Call<HotelResponseData>,
                        response: Response<HotelResponseData>
                    ) {
                        if(response.isSuccessful){
                            val result : HotelResponseData? = response.body()

                            if(result != null){
                                Toast.makeText(this@SelAddressWriteActivity, "성공적으로 숙소 주소를 수정하였습니다.", Toast.LENGTH_SHORT).show()

                                bundle?.putString("addressInfo",change_address)

                                val selLocationFragment = SelLocationFragment()
                                selLocationFragment.arguments = bundle

                                // 설정완료 버튼 클릭시 판매자 메인 화면으로 이동
//                                val intent = Intent(this@SelAddressWriteActivity, MainActivity::class.java)
//                                startActivity(intent)

                                finish()
                            }
                            else{
                                Toast.makeText(this@SelAddressWriteActivity, "잠시후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this@SelAddressWriteActivity, "잠시후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(call: Call<HotelResponseData>, t: Throwable) {
                        Toast.makeText(this@SelAddressWriteActivity, "잠시후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()

                    }
                })
            } else {
                Toast.makeText(this, "입력되지 않은 주소가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}