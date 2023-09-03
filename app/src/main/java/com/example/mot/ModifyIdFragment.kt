package com.example.mot

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mot.*
import com.example.mot.databinding.FragmentModifyIdBinding
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ModifyIdFragment : Fragment(){

    lateinit var binding: FragmentModifyIdBinding

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
        binding = FragmentModifyIdBinding.inflate(inflater, container, false)

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
            var textcertifi = binding.certifiInputEt.text.toString().toInt()
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
            var texttel = binding.telInputEt.text.toString()
            var textcertifi = binding.certifiInputEt.text.toString().toInt()
            var dialog = context?.let { it1 -> AlertDialog.Builder(it1) }

            if(binding.certifiChkTv.visibility == View.VISIBLE){
                Service.findId(Certifi(texttel, textcertifi)).enqueue(object : Callback<FindId> {
                    override fun onResponse(call: Call<FindId>, response: Response<FindId>) {
                        var result: FindId? = response.body()
                        if(result!=null){
                            if(result.id == 1){
                                val bundle = Bundle()
                                bundle.putString("loginId", result.loginId)
                                bundle.putString("createdAt", result.createdAt)

                                val chkIdFragment = ChkIdFragment()
                                chkIdFragment.arguments = bundle

                                (activity as ModifyActivity).changeFragment(chkIdFragment)

                            }
                            else if(result.id == 0){
                                (activity as ModifyActivity).changeFragment(ChkErrorFragment())
                            }
                            else{
                                dialog?.setTitle("통신 실패")
                                dialog?.setMessage("통신에 실패하였습니다.")
                                dialog?.show()
                            }
                        }
                    }
                    override fun onFailure(call: Call<FindId>, t: Throwable) {
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
