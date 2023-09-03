package com.example.mot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentModifyPwBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModifyPwFragment : Fragment(){

    lateinit var binding: FragmentModifyPwBinding

    val httpClient = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor()) // Add your custom interceptor
        .build()

    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.85.98:8080")//서버 주소를 적을 것
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var Service = retrofit.create(Service::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModifyPwBinding.inflate(inflater, container, false)


        binding.telBtnIb.setOnClickListener {
            var texttel = binding.telInputEt.text.toString()

            Service.requestCertifi(texttel).enqueue(object : Callback<Certifi> {
                override fun onResponse(call: Call<Certifi>, response: Response<Certifi>) {
                    var result: Certifi? = response.body()
                    if(result!=null){
                        if(verifyTel(texttel)){
                            setTelStatus(true)
                        }
                        else{
                            setTelStatus(false)
                        }
                    }
                }
                override fun onFailure(call: Call<Certifi>, t: Throwable) {
                    setTelStatus(false)
                }
            })
        }

        binding.certifiBtnIb.setOnClickListener {
            var texttel = binding.telInputEt.text.toString()
            var textcertifi = binding.certifiInputEt.text.toString()?.toInt()
            Service.chkRandomNum(Certifi(texttel, textcertifi)).enqueue(object : Callback<Check> {
                override fun onResponse(call: Call<Check>, response: Response<Check>) {
                    var result: Check? = response.body()
                    if(result!=null){
                        if(result.check == true){
                            setCertiStatus(true)
                        }
                        else{
                            setCertiStatus(false)
                        }
                    }
                }
                override fun onFailure(call: Call<Check>, t: Throwable) {
                    setCertiStatus(false)
                }
            })
        }

        binding.btnIb.setOnClickListener {
            val textId = binding.idInputEt.text.toString()
            val textTel = binding.telInputEt.text.toString()
            val textCertifi = binding.certifiInputEt.text.toString().toInt()
            var dialog = context?.let { it1 -> AlertDialog.Builder(it1) }

            if(binding.certifiChkTv.visibility == View.VISIBLE){
                (activity as ModifyActivity).changeFragment(ModifyNewPwFragment())
                    Service.chkId(ChkId(textId, textTel, textCertifi)).enqueue(object : Callback<Check>{
                        override fun onResponse(call: Call<Check>, response: Response<Check>) {
                            val result: Check? = response.body()
                            if(result != null){
                                if(result.check == true){
                                    (activity as ModifyActivity).changeFragment(ModifyNewPwFragment())
                                }
                                else if(result.check == false){
                                    (activity as ModifyActivity).changeFragment(ChkErrorFragment())
                                }
                            }
                        }

                        override fun onFailure(call: Call<Check>, t: Throwable) {
                            dialog?.setTitle("통신 실패")
                            dialog?.setMessage("통신에 실패하였습니다.")
                            dialog?.show()
                        }

                    })
            }
        }
        return binding.root

    }

    //tel유효성 검사
    fun verifyTel(tel: String) : Boolean {
        val regexTel = """^\d{11}$""".toRegex()
        return regexTel.matches(tel)
    }

    private fun setTelStatus(isAvailable: Boolean){
        if(isAvailable){
            binding.telUnavailableTv.visibility = View.GONE
        }
        else{
            binding.telUnavailableTv.visibility = View.VISIBLE
        }
    }

    private fun setCertiStatus(isAvailable: Boolean){
        if(isAvailable){
            binding.certifiErrorTv.visibility = View.GONE
            binding.certifiChkTv.visibility = View.VISIBLE
        }
        else{
            binding.certifiErrorTv.visibility = View.VISIBLE
            binding.certifiChkTv.visibility = View.GONE
        }
    }
}