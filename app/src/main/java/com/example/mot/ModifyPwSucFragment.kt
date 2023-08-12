package com.example.mot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentModifyPwSucBinding

class ModifyPwSucFragment: Fragment() {
    lateinit var binding: FragmentModifyPwSucBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModifyPwSucBinding.inflate(inflater, container, false)

//        binding.btnIb.setOnClickListener {
//
//        }
        return binding.root
    }
}