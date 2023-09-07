package com.example.mot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentInqRoominfoDetailsBinding

class InqRoominfoDetailsFragment : Fragment() {

    private lateinit var bindig: FragmentInqRoominfoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindig = FragmentInqRoominfoDetailsBinding.inflate(layoutInflater, container, false)

        return bindig.root
    }

}