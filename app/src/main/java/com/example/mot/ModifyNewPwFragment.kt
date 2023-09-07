package com.example.mot

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentModifyNewPwBinding
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModifyNewPwFragment: Fragment(){
    lateinit var binding: FragmentModifyNewPwBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModifyNewPwBinding.inflate(inflater, container, false)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor()) // Add your custom interceptor
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.85.98:8080")//서버 주소를 적을 것
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var Service = retrofit.create(Service::class.java)

        binding.pwInputEt.addTextChangedListener(object : TextWatcher{
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

        binding.btnIb.setOnClickListener {
            val textpw = binding.pwChkInputEt.text.toString()
            var dialog = context?.let { it1 -> AlertDialog.Builder(it1) }

            if(binding.pwDifferentTv.visibility == View.GONE){
                Service.modifyPw(textpw).enqueue(object : Callback<ResponseBody>{
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        val result: ResponseBody? = response.body()
                        if(result!=null){
                            (activity as ModifyActivity).changeFragment(ModifyPwSucFragment())
                        }
                        else{
                            dialog?.setTitle("변경 실패")
                            dialog?.setMessage("비밀번호 변경에 실패하였습니다.")
                            dialog?.show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        dialog?.setTitle("통신 실패")
                        dialog?.setMessage("통신에 실패하였습니다.")
                        dialog?.show()
                    }

                })

            }
        }
        return binding.root
    }

    //pw유효성 검사
    fun verifyPw(pw: String) : Boolean {
        val regexPw = """^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#${'$'}%^&+=]).{4,16}$""".toRegex()
        return regexPw.matches(pw)
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
}