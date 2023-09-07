package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityPaymentBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaymentActivity    : AppCompatActivity() {

        private var mBinding: ActivityPaymentBinding? = null
        private val binding get() = mBinding!!

        var retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.85.98:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        var service = retrofit.create(Service::class.java)

        var hotelId : Int? = null
        var roomId : List<Int>? = null
        var packageId : List<Int>? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)


            //예약 생성
//            service.reserveCreate(ReserveData(mBinding!!.checkinBtn.toString(), mBinding!!.checkoutBtn.toString(), mBinding!!.price.toString(),
//                /*이전 액티비티로 받아옴 */ 60, mBinding!!.phoneNumber.toString(), hotelId!!.toInt(), roomId!!.toList(),packageId!!.toList()))
//                .enqueue(object : Callback<ReserveResponseData>{
//                    override fun onResponse(
//                        call: Call<ReserveResponseData>,
//                        response: Response<ReserveResponseData>
//                    ) {
//                        if (response.isSuccessful){
//                            var result : ReserveResponseData? = response.body()
//
//                            if(result != null){
//                                mBinding!!.checkinBtn.text = result.checkIn
//                                mBinding!!.checkinBtn.text = result.checkOut
//                                mBinding!!.phoneNumber.text = result.phone
//
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ReserveResponseData>, t: Throwable) {
//
//                    }
//                })

            mBinding = ActivityPaymentBinding.inflate(layoutInflater)
            setContentView(binding.root)

            mBinding!!.more.setOnClickListener {
                val intent =  Intent(this,PrivacyActivity::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            mBinding!!.more1.setOnClickListener {
                val intent =  Intent(this,PrivacyActivity::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            mBinding!!.moreReservation.setOnClickListener {
                val intent =  Intent(this,AccommodationActivity::class.java)
                startActivity(intent)
                finish()

            }
            mBinding!!.backbutton.setOnClickListener {
                val intent =  Intent(this,AccommodationActivity::class.java)
                startActivity(intent)
                finish()
            }

            mBinding!!.changeStr.setOnClickListener {
                mBinding!!.plusInfoPage.visibility = View.VISIBLE
            }

            mBinding!!.x.setOnClickListener {
                mBinding!!.roomLayout.visibility = View.GONE
            }
            mBinding!!.x2.setOnClickListener {
                mBinding!!.roomLayout2.visibility = View.GONE
            }

            var essCb_flag = false

            mBinding!!.agreeStr.setOnCheckedChangeListener { _, isChecked ->
                if (!essCb_flag) {
                    mBinding!!.checkBoxSmall.isChecked = isChecked
                    mBinding!!.checkBoxSmall1.isChecked = isChecked
                }
                else {
                    essCb_flag = false
                }
            }

            mBinding!!.checkBoxSmall.setOnCheckedChangeListener { _, isChecked ->
                if (mBinding!!.agreeStr.isChecked && !mBinding!!.checkBoxSmall.isChecked) {
                    essCb_flag = true
                }
                mBinding!!.agreeStr.isChecked = mBinding!!.checkBoxSmall.isChecked && mBinding!!.checkBoxSmall1.isChecked
            }

            mBinding!!.checkBoxSmall1.setOnCheckedChangeListener { _, isChecked ->
                if (mBinding!!.agreeStr.isChecked && !mBinding!!.checkBoxSmall1.isChecked) {
                    essCb_flag = true
                }
                mBinding!!.agreeStr.isChecked = mBinding!!.checkBoxSmall.isChecked && mBinding!!.checkBoxSmall1.isChecked
            }

        }
}