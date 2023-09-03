package com.example.mot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.mot.databinding.ActivityMyaccountBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyAccountActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyaccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyaccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val httpClient = OkHttpClient.Builder()
//            .addInterceptor(TokenInterceptor()) // Add your custom interceptor
//            .build()
//
//        var retrofit = Retrofit.Builder()
//            .baseUrl("http://13.125.85.98:8080")//서버 주소를 적을 것
//            .client(httpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        var Service = retrofit.create(Service::class.java)


//        Service.getaccount().enqueue(object : Callback<RequestAccount>{
//
//            var dialog = AlertDialog.Builder(this@MyAccountActivity)
//
//            override fun onResponse(call: Call<RequestAccount>, response: Response<RequestAccount>
//            ) {
//                val result = response.body()
//                if(result != null){
//                    if(result.id == 1){
//                        binding.nameTv.text = result.name
//                        binding.bankNameTv.text = result.bank
//                        binding.accountNameTv.text = result.number
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<RequestAccount>, t: Throwable) {
//                dialog.setTitle("설정 실패")
//                dialog.setMessage("계좌 설정에 실패하였습니다.")
//                dialog.show()
//            }
//
//        })

        binding.backIv.setOnClickListener {
            finish()
        }

        binding.btnIv.setOnClickListener {
            val intent = Intent(this, AccountSettingActivity::class.java)
            startForResult.launch(intent)
        }


    }

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            var Accounter = result.data?.getStringExtra("accounter")
            var Bank = result.data?.getStringExtra("bank")
            var Account = result.data?.getStringExtra("account")

            binding.nameTv.text = Accounter
            binding.bankNameTv.text = Bank
            binding.accountNameTv.text = Account
        }
    }
}