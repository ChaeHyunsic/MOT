package com.example.mot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.mot.CertifiActivity
import com.example.mot.databinding.ActivityJoinBinding
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

//        var retrofit = Retrofit.Builder()
//            .baseUrl("")//서버 주소를 적을 것
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        var Service = retrofit.create(Service::class.java)


        //아이디 정규표현식 및 중복확인 기능
        binding.dupliBtnIb.setOnClickListener{
            var textid = binding.idInputEt.text.toString()

            if(verifyId(textid)){
                setIdStatus(true)
            }
            else{
                setIdStatus(false)
            }

            //아이디 중복 retrofit2
//            Service.requestId(textid).enqueue(object : Callback<Id_Duplic> {
//                override fun onResponse(call: Call<Id_Duplic>, response: Response<Id_Duplic>) {
//                    //응답값을 받아왓을 때
//                    var dialog = AlertDialog.Builder(this@JoinActivity)
//                    var duplic_code = response.code() //서버에서 받은 코드값을 duplic_code 객체에 넣음
//                    if(duplic_code == 200){
//                        if(verifyId(textid)){
//                            setIdStatus(true)
//                        }
//                        else{
//                            setIdStatus(false)
//                        }
//                    }
//                    else{
//                        dialog.setTitle("실패")
//                        dialog.setMessage("통신에 실패하였습니다.")
//                        dialog.show()
//                    }
//                }
//
//                override fun onFailure(call: Call<Id_Duplic>, t: Throwable) {
//                    //웹통신이 실패했을 시
//                    var dialog = AlertDialog.Builder(this@JoinActivity)
//                    dialog.setTitle("실패")
//                    dialog.setMessage("통신에 실패하였습니다.")
//                    dialog.show()
//
//                }
//
//            })
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