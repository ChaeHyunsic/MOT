package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentPackageinfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PackageinfoFragment : Fragment() {

    private lateinit var binding: FragmentPackageinfoBinding

    var packageId: Int?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.85.98:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(Service::class.java)

        binding = FragmentPackageinfoBinding.inflate(layoutInflater, container, false)

        // 패키지 조회
        service.packageInq(packageId!!.toInt()).enqueue(object : Callback<ResponsePackageData> {
            override fun onResponse(
                call: Call<ResponsePackageData>,
                response: Response<ResponsePackageData>
            ) {
                if(response.isSuccessful){
                    val result : ResponsePackageData? = response.body()

                    if(result != null){
                        binding.roomInfoStr.text = result.roomType
                        binding.personnel.text = "기존" + result.minPeople +"/ 최대" + result.maxPeople
                        binding.allPriceInt.text = result.price.toString()+ "원"
                    }
                }
            }

            override fun onFailure(call: Call<ResponsePackageData>, t: Throwable) {
                Toast.makeText(context, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
            }
        })

        binding.packageBtn.setOnClickListener {
            val intent = Intent(activity, Tablayout_details_Activity::class.java )

            startActivity(intent)
        }

        binding.packageBtn2.setOnClickListener {
            val intent = Intent(activity, Tablayout_details_Activity::class.java )

            startActivity(intent)
        }

        return binding.root
    }
}