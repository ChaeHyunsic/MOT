package com.example.mot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class LocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        childFragmentManager
            .beginTransaction()
            .replace(R.id.map, MapsFragment())
            .commit()

        return inflater.inflate(R.layout.fragment_location, container, false)
    }

}