package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mot.databinding.FragmentSelReviewListBinding
import com.example.mot.databinding.SelReviewListItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelReviewListFragment : Fragment() {

    private lateinit var binding : FragmentSelReviewListBinding

    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.85.98:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(Service::class.java)
    var commentId : Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
        selReviewInqBinding.btnHide.setOnClickListener {
            service.commentVis(/*TokenManager.getAuthToken(),*/ComVisibleData(commentId!!.toInt(), visible = false)).enqueue(object :
                 Callback<ComVisibleChange>{
                override fun onResponse(
                    call: Call<ComVisibleChange>,
                    response: Response<ComVisibleChange>
                ) {
                    if(response.isSuccessful){
                        val result = response.body()

                        if(result != null){
                            selReviewInqBinding.btnHide.setImageResource(R.drawable.btn_hide_done)
                        }
                    }
                }

                override fun onFailure(call: Call<ComVisibleChange>, t: Throwable) {
                    Toast.makeText(activity, "잠시후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
            })
        }

        selReviewInqBinding.btnWriteReply.setOnClickListener {
            val intent = Intent(activity, SelReplyActivity::class.java)
            startActivity(intent)

            Handler(Looper.getMainLooper()).postDelayed({
                selReviewInqBinding.btnWriteReply.text = "답변 완료"
                selReviewInqBinding.btnWriteReply.isEnabled = false
            }, 1000)
        }

        selReviewInqBinding.btnMore.setOnClickListener {
            val txt = selReviewInqBinding.selReviewBox.text.toString()
            val intent = Intent(activity, SelReviewMoreActivity::class.java)

            intent.putExtra("review", txt)
            startActivity(intent)
        }

        return binding.root
    }
}