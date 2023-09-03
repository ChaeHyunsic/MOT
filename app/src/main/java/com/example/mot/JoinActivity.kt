package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.*
import com.example.mot.databinding.ActivityJoinBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JoinActivity : AppCompatActivity() {

    lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)

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


        //아이디 정규표현식 및 중복확인 기능
        binding.dupliBtnIb.setOnClickListener{
            var textid = binding.idInputEt.text.toString()
            var dialog = AlertDialog.Builder(this@JoinActivity)

            //아이디 중복 retrofit2
            Service.requestId(textid).enqueue(object : Callback<Check> {
                override fun onResponse(call: Call<Check>, response: Response<Check>) {
                    var result: Check? = response.body() //서버에서 받은 코드값을 duplic_code 객체에 넣음
                    if(result != null){
                        if(result.check == true){
                            if(verifyId(textid)){
                                setIdStatus(true)
                            }
                            else{
                                setIdStatus(false)
                            }
                        }
                        else{
                            setIdStatus(false)
                        }
                    }
                    else{
                        setIdStatus(false)
                    }

                }

                override fun onFailure(call: Call<Check>, t: Throwable) {
                    //웹통신이 실패했을 시
                    dialog.setTitle("통신 실패")
                    dialog.setMessage("통신에 실패하였습니다.")
                    dialog.show()

                }

            })
        }

        //비밀번호 입력시 정규표현식 확인 기능
        binding.pwInputEt.addTextChangedListener(object : TextWatcher {
            //입력이 끝날때 작동
            override fun afterTextChanged(p0: Editable?) {
                var textpw = binding.pwInputEt.text.toString()
                if(verifyPw(textpw)){
                    setPwStatus(true)
                }
                else{
                    setPwStatus(false)
                }
            }
            //입력하기전 작동
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setPwStatus(true)
            }
            //텍스트 변화 있을 시 작동
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var textpw = binding.pwInputEt.text.toString()
                if(verifyPw(textpw)){
                    setPwStatus(true)
                }
                else{
                    setPwStatus(false)
                }
            }
        })

        //엔터키 입력시 비밀번호 일치 여부 확인
        binding.pwChkInputEt.setOnKeyListener { v, keyCode, event ->
            var textpw = binding.pwInputEt.text.toString()
            var textpwchk = binding.pwChkInputEt.text.toString()
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER){
                if(textpw == textpwchk){
                    setPwChkStatus(true)
                }
                else{
                    setPwChkStatus(false)
                }
            }
            return@setOnKeyListener false
        }

        //인증번호 확인 여부
        binding.telBtnIb.setOnClickListener{
            var texttel = binding.telInputEt.text.toString()

            if(verifyTel(texttel)){
                setTelStatus(true)
                val intent = Intent(this, CertifiActivity::class.java)
                intent.putExtra("tel", texttel)
                startActivity(intent)
            }
            else{
                setTelStatus(false)
            }
        }

        binding.btnIb.setOnClickListener {
            var textid = binding.idInputEt.text.toString()
            var textpw = binding.pwInputEt.text.toString()
            var texttel = binding.telInputEt.text.toString()
            var dialog = AlertDialog.Builder(this@JoinActivity)

            Service.requestJoin(JoinRequestData(textid, textpw, texttel)).enqueue(object : Callback<JoinResponseData>{
                override fun onResponse(call: Call<JoinResponseData>, response: Response<JoinResponseData>) {
                    var result: JoinResponseData? = response.body()
                    if(result != null){
                        if(result.id == 1){
                            //페이지 이동
                        }
                        else{
                            dialog.setTitle("가입 실패")
                            dialog.setMessage("가입에 실패하였습니다.")
                            dialog.show()
                        }
                    }
                    else{
                        dialog.setTitle("가입 실패")
                        dialog.setMessage("가입에 실패하였습니다.")
                        dialog.show()
                    }
                }

                override fun onFailure(call: Call<JoinResponseData>, t: Throwable) {
                    dialog.setTitle("가입 실패")
                    dialog.setMessage("가입에 실패하였습니다.")
                    dialog.show()
                }
            })
        }
    }

    //id유효성 검사
    fun verifyId(id: String) : Boolean {
        val regexId = """^[a-z0-9]{4,12}""".toRegex()
        return regexId.matches(id)
    }

    //pw유효성 검사
    fun verifyPw(pw: String) : Boolean {
        val regexPw = """^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#${'$'}%^&+=]).{4,16}$""".toRegex()
        return regexPw.matches(pw)
    }

    //tel유효성 검사
    fun verifyTel(tel: String) : Boolean {
        val regexTel = """^\d{11}$""".toRegex()
        return regexTel.matches(tel)
    }

    //id상태 msg
    private fun setIdStatus(isAvailable: Boolean){
        if(isAvailable){
            binding.idAvailableTv.visibility = View.VISIBLE
            binding.idUnavailableTv.visibility = View.GONE
        }
        else{
            binding.idAvailableTv.visibility = View.GONE
            binding.idUnavailableTv.visibility = View.VISIBLE
        }
    }

    //pw상태 msg
    private fun setPwStatus(isAvailable: Boolean){
        if(isAvailable){
            binding.pwUnavailableTv.visibility = View.GONE
        }
        else{
            binding.pwUnavailableTv.visibility = View.VISIBLE
        }
    }

    //pw_chk상태 msg
    private fun setPwChkStatus(isAvailable: Boolean){
        if(isAvailable){
            binding.pwDifferentTv.visibility = View.GONE
        }
        else{
            binding.pwDifferentTv.visibility = View.VISIBLE
        }
    }

    private fun setTelStatus(isAvailable: Boolean){
        if(isAvailable){
            binding.telUnavailableTv.visibility = View.GONE
        }
        else{
            binding.telUnavailableTv.visibility = View.VISIBLE
        }
    }

}