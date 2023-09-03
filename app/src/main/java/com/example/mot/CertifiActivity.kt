package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityCertifiBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CertifiActivity : AppCompatActivity() {


    lateinit var binding: ActivityCertifiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCertifiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor()) // Add your custom interceptor
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.85.98:8080")//서버 주소를 적을 것
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var Service = retrofit.create(Service::class.java)

        binding.btnIb.setOnClickListener {
            finish()
        }

        //인증번호 확인시 뒤로가기
        binding.btnIb.setOnClickListener{
            var textcertifi = binding.certifiInputEt.text.toString().toInt()
            var phone = intent.getStringExtra("tel")
            var dialog = AlertDialog.Builder(this@CertifiActivity)
            Service.requestCertifi(phone).enqueue(object : Callback<Certifi>{
                override fun onResponse(call: Call<Certifi>, response: Response<Certifi>) {
                    var result: Certifi? = response.body()
                    if(result!=null){
                        if(result.randomNumber == textcertifi){
                            dialog.setTitle("성공")
                            dialog.setMessage("인증되었습니다.")
                            dialog.show()
                            finish()
                        }
                        else{
                            dialog.setTitle("실패")
                            dialog.setMessage("인증에 실패하였습니다.${result.randomNumber}")
                            dialog.show()
                        }
                    }
                }

                override fun onFailure(call: Call<Certifi>, t: Throwable) {
                    var dialog = AlertDialog.Builder(this@CertifiActivity)
                    dialog.setTitle("실패")
                    dialog.setMessage("인증에 실패하였습니다.")
                    dialog.show()
                }

            })
        }
    }
}