package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentSelLocationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class SelLocationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentSelLocationBinding

    private lateinit var mapView : MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentSelLocationBinding.inflate(layoutInflater)

        mapView = binding.map
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        // 거리 수정
        binding.distanceEdit.setOnClickListener {
            val intent = Intent (activity, SelLocationDetailActivity::class.java)

            startActivity(intent)
        }

        // 수정한 거리 정보 적용
        if (arguments?.getString("locationInfo") != null) {
            binding.locationStr.text = arguments?.getString("locationInfo").toString()
        }


        // 교통 정보 수정
        binding.trafficEdit.setOnClickListener {
            val intent = Intent(activity, SelTrafficDetailActivity::class.java )

            startActivity(intent)
        }

        // 수정한 교통 정보 적용
        if (arguments?.getString("trafficInfo") != null) {
            binding.tvTrafficWay.text = arguments?.getString("trafficInfo").toString()
        }


        // 주소 수정
        binding.addressEdit.setOnClickListener {
            val intent = Intent(activity, SelAreaChooseActivity::class.java)

            startActivity(intent)
        }

        // 수정한 위치 정보 적용
        if (arguments?.getString("addressInfo") != null) {
            binding.adressDetail.text = arguments?.getString("addressInfo").toString()
        }


        return binding.root
    }

    override fun onMapReady(googleMap : GoogleMap) {
        val myLocation = LatLng(37.654601, 127.060530)

        // 지도 초기화면 설정
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 17f))



        //마커 출력
        val marker = MarkerOptions()
            .position(myLocation)
        googleMap.addMarker(marker)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }


    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}