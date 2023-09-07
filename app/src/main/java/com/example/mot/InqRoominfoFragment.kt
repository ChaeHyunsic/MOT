package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentInqRoominfoBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RoominfoFragment : Fragment() {
    private lateinit var binding: FragmentInqRoominfoBinding

    var roomId: Int?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.85.98:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(Service::class.java)

        binding = FragmentInqRoominfoBinding.inflate(layoutInflater, container, false)

//        //객실 조회
//        service.roomInq(roomId!!.toInt()).enqueue(object : Callback<ResponseInqRoomData>{
//            override fun onResponse(
//                call: Call<ResponseInqRoomData>,
//                response: Response<ResponseInqRoomData>
//            ) {
//                if(response.isSuccessful){
//                    val result :ResponseInqRoomData? = response.body()
//
//                    if(result != null){
//                        binding.roomInfoStr.text=result.roomType
//                        binding.personnel.text="기존" + result.minPeople +"/ 최대" + result.maxPeople
//                        binding.allPriceInt.text=result.Price.toString() +"원"
//
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseInqRoomData>, t: Throwable) {
//                Toast.makeText(context, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
//            }
//        })

        binding.packageBtn.setOnClickListener {
            (activity as Tablayout_Activity).changeFragment(InqRoominfoDetailsFragment())
        }
        binding.packageBtn2.setOnClickListener {
            val intent = Intent(activity, Tablayout_details_Activity::class.java )

            startActivity(intent)
        }
        binding.packageBtn3.setOnClickListener {
            val intent = Intent(activity, Tablayout_details_Activity::class.java )

            startActivity(intent)
        }


        return binding.root
    }


}