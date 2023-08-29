package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelJoinNameBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelJoinNameActivity : AppCompatActivity() {

    private var mBinding: ActivitySelJoinNameBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelJoinNameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (mBinding!!.accomNameTv.text.toString() != "") {
                    mBinding!!.dupliBtn.setBackgroundResource(R.drawable.btn_review)
                    mBinding!!.dupliBtn.setTextAppearance(R.style.splash_style)
                    mBinding!!.dupliBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11F)
                }
                else {
                    mBinding!!.dupliBtn.setBackgroundResource(R.drawable.dupli_check)
                    mBinding!!.dupliBtn.setTextAppearance(R.style.anno_style)
                    mBinding!!.dupliBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11F)
                }
            }
        }

        mBinding!!.accomNameTv.addTextChangedListener(textWatcher)

        mBinding!!.dupliBtn.setOnClickListener {
            val value: String = mBinding!!.accomNameTv.text.toString()

            if (value.isEmpty()) {
                mBinding!!.accomNameTv.error = "숙소명이 입력되지 않았습니다"
            }
            else {
                var retrofit = Retrofit.Builder()
                    .baseUrl("http://13.125.85.98:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                var service = retrofit.create(Service::class.java)

                service.search_name(value).enqueue(object :
                    Callback<List<inq_accommodation>> {
                    override fun onResponse(call: Call<List<inq_accommodation>>, response: Response<List<inq_accommodation>>) {
                        if(response.isSuccessful){
                            var results: List<inq_accommodation>? = response.body()

                            if (results?.isNullOrEmpty() == true) {
                                mBinding!!.accomNameTv.error = null
                                Toast.makeText(this@SelJoinNameActivity,"등록 가능한 숙소명입니다", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                mBinding!!.accomNameTv.error = "이미 등록되어 있는 숙소명입니다"
                            }
                        }
                        else {
                            Toast.makeText(this@SelJoinNameActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<List<inq_accommodation>>, t: Throwable) {
                        Toast.makeText(this@SelJoinNameActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        mBinding!!.confirmBtn.setOnClickListener {
            if (mBinding!!.accomNameTv.error == null) {
                val intent = Intent(this, SelJoinLocationActivity::class.java)
                intent.putExtra("accom_name", mBinding!!.accomNameTv.text.toString())

                startActivity(intent)
            }
            else {
                Toast.makeText(this,"숙소명이 등록되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}