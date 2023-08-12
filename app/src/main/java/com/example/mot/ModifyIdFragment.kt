package com.example.mot

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mot.ChkIdFragment
import com.example.mot.ModifyActivity
import com.example.mot.databinding.FragmentModifyIdBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ModifyIdFragment : Fragment(){

    lateinit var binding: FragmentModifyIdBinding

//    var retrofit = Retrofit.Builder()
//            .baseUrl("")//서버 주소를 적을 것
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    var Service = retrofit.create(Service::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModifyIdBinding.inflate(inflater, container, false)

        binding.telBtnIb.setOnClickListener {
            var texttel = binding.telInputEt.text.toString()

            if(verifyTel(texttel)){
                setTelStatus(true)
            }
            else{
                setTelStatus(false)
            }
        }

        binding.certifiBtnIb.setOnClickListener {
            var textcertifi = binding.certifiInputEt.text.toString()
            setCertiStatus(true)
//                Service.requestCertifi(textcertifi).enqueue(object : Callback<Certifi> {
//                override fun onResponse(call: Call<Certifi>, response: Response<Certifi>) {
//                    var code = response.code()
//                    if(code == 200){
//                        setCertiStatus(true)
//                    }
//                    else{
//                        setCertiStatus(false)
//                    }
//                }
//                override fun onFailure(call: Call<Certifi>, t: Throwable) {
//                    setCertiStatus(false)
//                }
//                })
        }

        binding.btnIb.setOnClickListener {
            if(binding.certifiChkTv.visibility == View.VISIBLE){
                (activity as ModifyActivity).changeFragment(ChkIdFragment())
//                    Service.requestId().enqueue(object : Callback<Id> {
//                        override fun onResponse(call: Call<Id>, response: Response<Id>) {
//                            var code = response.code()
//                            if(code == 200){
//                                (activity as ModifyActivity).changeFragment(ChkIdFragment())
//                            }
//                            else{
//                                (activity as ModifyActivity).changeFragment(ChkErrorFragment())
//                            }
//                        }
//                        override fun onFailure(call: Call<Id>, t: Throwable) {
//                            (activity as ModifyActivity).changeFragment(ChkErrorFragment())
//                        }
//                    })
                }
        }

//        binding.telBtnIb.setOnClickListener(this)
//        binding.certifiBtnIb.setOnClickListener(this)
//        binding.btnIb.setOnClickListener(this)

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
