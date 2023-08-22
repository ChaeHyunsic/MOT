package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private var mBinding: ActivityLoginBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.85.98:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

       var service = retrofit.create(Service::class.java)

        mBinding!!.findTv.setOnClickListener {
            val intent = Intent(this, ModifyActivity::class.java)
            startActivity(intent)
        }

        mBinding!!.loginBtn.setOnClickListener {
            service.login(LoginRequestData(mBinding!!.idTv.text.toString(), mBinding!!.pwTv.text.toString())).enqueue(object :
                Callback<LoginData> {
                override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                    if(response.isSuccessful){
                        var result: LoginData? = response.body()

                        if (result != null) {
                            if (result.purchaseMember) {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                            }
                            else {
                                val intent = Intent(this@LoginActivity, SelSplashActivity::class.java)
                                startActivity(intent)
                            }
                        }
                        else {
                            Toast.makeText(this@LoginActivity, "일치하는 로그인 정보가 존재하지 않습니다. 다시 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                        Toast.makeText(this@LoginActivity, "일치하는 로그인 정보가 존재하지 않습니다. 다시 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginData>, t: Throwable) {
                    // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                    Toast.makeText(this@LoginActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                }
            })
        }

        mBinding!!.joinTv.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

    }
}