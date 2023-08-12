package com.example.mot

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mot.ModifyActivity
import com.example.mot.databinding.FragmentModifyNewPwBinding
import com.example.mot.ModifyPwSucFragment

class ModifyNewPwFragment: Fragment(){
    lateinit var binding: FragmentModifyNewPwBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModifyNewPwBinding.inflate(inflater, container, false)

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
            if(binding.pwDifferentTv.visibility == View.GONE){
                (activity as ModifyActivity).changeFragment(ModifyPwSucFragment())
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