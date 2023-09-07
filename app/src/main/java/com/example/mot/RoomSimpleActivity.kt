package com.example.mot

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityRoomSimpleBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RoomSimpleActivity: AppCompatActivity() {

    lateinit var binding: ActivityRoomSimpleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomSimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor()) // Add your custom interceptor
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.85.98:8080")//서버 주소를 적을 것
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(Service::class.java)

        binding.standPerson2Tv.setOnClickListener {
            val intent = Intent(this, PersonActivity::class.java)
            startForResult.launch(intent)
        }

        binding.maxPerson2Tv.setOnClickListener {
            val intent = Intent(this, PersonActivity::class.java)
            startForResult1.launch(intent)
        }

        binding.backIv.setOnClickListener {
            val textName = binding.roomNameEt.text.toString()
            val textPerson = binding.personEt.text.toString()
            val textBasic = binding.basicEt.text.toString()
            val textStand = binding.standPerson2Tv.text.toString()
            val textMax = binding.maxPerson2Tv.text.toString()

            var dialog = AlertDialog.Builder(this@RoomSimpleActivity)

            intent.putExtra("name", textName)
            intent.putExtra("person", textPerson)
            intent.putExtra("basic", textBasic)
            intent.putExtra("stand", textStand)
            intent.putExtra("max", textMax)
            setResult(RESULT_OK, intent);
            finish()

            val info = ProduceRoom(textName, textPerson.toInt(), textBasic.toInt(), textStand.toInt(), textMax.toInt())

            service.produceroom(info).enqueue(object : Callback<RequestProduceRoom> {
                override fun onResponse(call: Call<RequestProduceRoom>, response: Response<RequestProduceRoom>
                ) {
                    val result = response.body()
                    if(result != null){
                        if(result.id != null){
                            dialog.setTitle("객실 생성 성공")
                            dialog.setMessage("객실 생성에 성공하였습니다. ")
                            dialog.show()
                            intent.putExtra("name", textName)
                            intent.putExtra("person", textPerson)
                            intent.putExtra("basic", textBasic)
                            intent.putExtra("stand", textStand)
                            intent.putExtra("max", textMax)
                            setResult(RESULT_OK, intent);
                            finish()
                        }
                    }else{
                        dialog.setTitle("객실 생성 실패")
                        dialog.setMessage("객실 생성에 실패하였습니다.")
                        dialog.show()
                    }
                }

                override fun onFailure(call: Call<RequestProduceRoom>, t: Throwable) {
                    dialog.setTitle("통신 실패")
                    dialog.setMessage("통신에 실패하였습니다.")
                    dialog.show()
                }
            })
        }
    }

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            binding.standPerson2Tv.text = result.data?.getStringExtra("num")
        }
        else{
            binding.standPerson2Tv.text = "0"
        }
    }

    val startForResult1 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            binding.maxPerson2Tv.text = result.data?.getStringExtra("num")
        }
        else{
            binding.maxPerson2Tv.text = "0"
        }
    }
}