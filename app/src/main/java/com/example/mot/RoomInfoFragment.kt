package com.example.mot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.mot.RoomDetailActivity
import com.example.mot.databinding.FragmentRoomInfoBinding
import com.example.seller.RoomSimpleActivity

class RoomInfoFragment: Fragment() {

    lateinit var binding: FragmentRoomInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomInfoBinding.inflate(inflater, container, false)


        binding.roomModifyIv.setOnClickListener {
            var intent = Intent(context, RoomDetailActivity::class.java)
            startActivity(intent)
        }

        binding.roomAddCl.setOnClickListener {
            var intent = Intent(activity, RoomSimpleActivity::class.java)
            startForResult.launch(intent)
        }

        return binding.root
    }


    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            // 결과가 성공적으로 반환되었을 때의 처리
            binding.room1Cl.visibility = View.VISIBLE
            binding.room1Tv.text = result.data?.getStringExtra("name")
            binding.add1PriceTv.text = result.data?.getStringExtra("person")
            binding.basic1PriceTv.text = result.data?.getStringExtra("basic")
            binding.stand1Tv.text = result.data?.getStringExtra("stand")
            binding.max1Tv.text = result.data?.getStringExtra("max")

        } else {
            // 결과가 실패했거나 취소된 경우의 처리
        }
    }
}