package com.example.mot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentChkErrorBinding

class ChkErrorFragment: Fragment() {
    lateinit var binding: FragmentChkErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChkErrorBinding.inflate(inflater, container, false)

        return binding.root
    }

}