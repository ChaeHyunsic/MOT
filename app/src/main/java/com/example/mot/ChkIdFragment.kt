package com.example.mot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentChkIdBinding

class ChkIdFragment: Fragment() {
    lateinit var binding: FragmentChkIdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChkIdBinding.inflate(inflater, container, false)

        return binding.root
    }

}