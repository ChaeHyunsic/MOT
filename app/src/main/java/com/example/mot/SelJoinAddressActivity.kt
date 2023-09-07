package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelJoinAddressBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelJoinAddressActivity : AppCompatActivity() {

    private var mBinding: ActivitySelJoinAddressBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelJoinAddressBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val accom_name = intent.getStringExtra("accom_name")
        val accom_location = intent.getStringExtra("accom_location")

        mBinding!!.chosenLocationTv.setText(accom_location)

        mBinding!!.confirmBtn.setOnClickListener {
            if (mBinding!!.addressTv != null && mBinding!!.addressDetailTv != null) {

                var retrofit = Retrofit.Builder()
                    .baseUrl("http://13.125.85.98:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                var service = retrofit.create(Service::class.java)

                service.hotel_add(TokenManager.getAuthToken(), HotelRequestData(accom_name.toString(), accom_location.toString(),
                    mBinding!!.addressTv.text.toString(), mBinding!!.addressDetailTv.text.toString())).enqueue(object :
                    Callback<HotelResponseData> {
                    override fun onResponse(call: Call<HotelResponseData>, response: Response<HotelResponseData>) {
                        if(response.isSuccessful){
                            var result: HotelResponseData? = response.body()

                            if (result != null) {
                                Toast.makeText(this@SelJoinAddressActivity, "성공적으로 숙소를 등록하였습니다.", Toast.LENGTH_SHORT).show()

                                val intent = Intent(this@SelJoinAddressActivity, SellActivity::class.java)

                                startActivity(intent)
                            }
                            else {
                                Toast.makeText(this@SelJoinAddressActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                            }
                        }else{
                            Toast.makeText(this@SelJoinAddressActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<HotelResponseData>, t: Throwable) {
                        Toast.makeText(this@SelJoinAddressActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            else {
                Toast.makeText(this,"입력되지 않은 주소가 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}