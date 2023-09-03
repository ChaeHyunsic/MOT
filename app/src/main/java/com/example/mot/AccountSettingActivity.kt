package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.AccountCertifiActivity
import com.example.mot.databinding.ActivityAccoutSettingBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AccountSettingActivity: AppCompatActivity() {

    lateinit var binding: ActivityAccoutSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccoutSettingBinding.inflate(layoutInflater)
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

        binding.backIv.setOnClickListener {
            finish()
        }

        binding.btnIv.setOnClickListener {
            var textaccounter = binding.accounterInputEt.text.toString()
            intent.putExtra("accounter", textaccounter)

            var textbank = binding.bankInputEt.text.toString()
            intent.putExtra("bank", textbank)

            var textaccount = binding.accountInputEt.text.toString()
            intent.putExtra("account", textaccount)

            var account = Account(textaccounter, textbank, textaccount)

            var dialog = AlertDialog.Builder(this@AccountSettingActivity)

            var intent = Intent(this, AccountCertifiActivity::class.java)

//            Service.modifyaccount(account).enqueue(object : Callback<RequestAccount>{
//                override fun onResponse(call: Call<RequestAccount>, response: Response<RequestAccount>
//                ) {
//                    val result: RequestAccount? = response.body()
//                    if(result != null){
//                        if(result.id == 1){
////                            var certifi = result.certifi //바디에서 인증번호 꺼내오기
////                            intent1.putExtra("certifi", certifi)
//                            startForResult.launch(intent)
//                        }
//                        else{
//                            dialog.setTitle("설정 실패")
//                            dialog.setMessage("계좌 설정에 실패하였습니다.")
//                            dialog.show()
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<RequestAccount>, t: Throwable) {
//                    dialog.setTitle("통신 실패")
//                    dialog.setMessage("통신에 실패하였습니다.")
//                    dialog.show()
//                }
//
//            })
        }

    }

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            setResult(RESULT_OK, intent);
            finish()
        }
        else{

        }
    }
}