package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentSelReviewListBinding
import com.example.mot.databinding.SelReviewListItemBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelReviewListFragment : Fragment() {

    private lateinit var binding : FragmentSelReviewListBinding

    var commentId : Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.85.98:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(Service::class.java)

        binding = FragmentSelReviewListBinding.inflate(layoutInflater)


        val selReviewInqBinding = SelReviewListItemBinding.inflate(layoutInflater)

        // 판매자 후기 조회
//        service.selCommentInq().enqueue(object : Callback<SelCommentInqData>{
//            override fun onResponse(
//                call: Call<SelCommentInqData>,
//                response: Response<SelCommentInqData>
//            ) {
//                if (response.isSuccessful){
//                    var results = response.body()
//
//                    if (results != null){
//                        binding.tvNowGrade.text = results.hotelstar.toString()
//
//
////                        if(data != null) {
////                            selReviewInqBinding.selReviewNickname.text = data.name
////                            selReviewInqBinding.selRatingBar.rating = data.star.toFloat()
////                            selReviewInqBinding.selReviewUseroom.text = data.packageName + " / " + data!!.roomName
////                            selReviewInqBinding.selReviewBox.text = data.context
////
////                            binding.selReviewItemLayout.addView(selReviewInqBinding.root)
////                        }
//
//
//                    }
//                    Toast.makeText(context, "다시 시도해주세요.", Toast.LENGTH_SHORT).show()
//                }
//                Toast.makeText(context, "다시 시도해주세요.", Toast.LENGTH_SHORT).show()
//            }
//
//
//            override fun onFailure(call: Call<SelCommentInqData>, t: Throwable) {
//                Toast.makeText(context, "잠시후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
//            }
//        })

        // 숨김 버튼
        val select_buttons = listOf(binding.btnHide, binding.btnHide2)
        for (btn_hide in select_buttons) {
            btn_hide.setOnClickListener {
//            service.commentVis(/*TokenManager.getAuthToken(),*/ComVisibleData(commentId!!.toInt(), visible = false)).enqueue(object :
//                 Callback<ComVisibleChange>{
//                override fun onResponse(
//                    call: Call<ComVisibleChange>,
//                    response: Response<ComVisibleChange>
//                ) {
//                    if(response.isSuccessful){
//                        val result : ComVisibleChange? = response.body()
//
//                        if(result!!.visible == true){
//                            binding.btnHide.setImageResource(R.drawable.btn_hide_done)
//                        }
//                    }
//                    Toast.makeText(context, "잠시후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
//                }
//
//
//                override fun onFailure(call: Call<ComVisibleChange>, t: Throwable) {
//                    Toast.makeText(context, "잠시후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
//                }
//            })
                btn_hide.setImageResource(R.drawable.btn_hide_done)
            }
        }

        binding.btnWriteReply.setOnClickListener {
            val intent = Intent(activity, SelReplyActivity::class.java)
            startActivity(intent)

            Handler(Looper.getMainLooper()).postDelayed({
                binding.btnWriteReply.text = "답변 완료"
                binding.btnWriteReply.isEnabled = false
            }, 1000)
        }

        // 더보기
        val more = listOf(binding.btnMore, binding.btnMore2)
        for (btn_more in more) {
            btn_more.setOnClickListener {
                val txt = binding.selReviewBox.text.toString()
                val intent = Intent(activity, SelReviewMoreActivity::class.java)

                intent.putExtra("review", txt)
                startActivity(intent)
            }
        }

        return binding.root
    }
}