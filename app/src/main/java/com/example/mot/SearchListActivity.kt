package com.example.mot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mot.databinding.ActivitySearchListBinding
import com.example.mot.databinding.ResultAccommodationItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchListActivity : AppCompatActivity() {

    private var mBinding: ActivitySearchListBinding? = null
    private val binding get() = mBinding!!

    private var fitter1: String? = null
    private var fitter2: String? = null
    private var fitter3: String? = null

    private var start_date: String? = null
    private var end_date: String? = null

    private var person: String? = null

    private var keyword: String? = null

    private val getResultFitter = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            fitter1 = data?.getStringExtra("fitter 1")
            fitter2 = data?.getStringExtra("fitter 2")
            fitter3 = data?.getStringExtra("fitter 3")
        }
    }

    private val getResultDate = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            if (start_date != data?.getStringExtra("start_date") || end_date != data?.getStringExtra("end_date")) {
                start_date = data?.getStringExtra("start_date")
                end_date = data?.getStringExtra("end_date")

                mBinding!!.dateBtn.setText(start_date + "~" + end_date)

                var retrofit = Retrofit.Builder()
                    .baseUrl("http://13.125.85.98:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                var service = retrofit.create(Service::class.java)

                service.search(keyword, start_date, person!!.toInt(), end_date).enqueue(object :
                    Callback<List<inq_accommodation>> {
                    override fun onResponse(call: Call<List<inq_accommodation>>, response: Response<List<inq_accommodation>>) {
                        if(response.isSuccessful){
                            var results: List<inq_accommodation>? = response.body()

                            if (results != null) {
                                mBinding!!.resultLl.removeAllViews()
                                for (result in results) {
                                    val newCardViewBinding = ResultAccommodationItemBinding.inflate(layoutInflater)

                                    val url = result.photo

                                    Glide.with(this@SearchListActivity)
                                        .load(url) // 불러올 이미지 url
                                        .into(newCardViewBinding.resultImg1) // 이미지를 넣을 뷰

                                    newCardViewBinding.resultTitle1.text = result.name
                                    newCardViewBinding.resultRatingBar1.rating = result.star.toFloat()
                                    newCardViewBinding.resultRating1.text = result.star.toString()
                                    newCardViewBinding.resultPrice1.text = result.price.toString() + " 원"
                                    newCardViewBinding.resultTotal1.text = (result.price * person!!.toInt()).toString() + " 원"

                                    mBinding!!.resultLl.addView(newCardViewBinding.root)
                                }
                            }
                            else {
                                mBinding!!.resultLl.removeAllViews()
                            }
                        }
                        else {
                            Toast.makeText(this@SearchListActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<List<inq_accommodation>>, t: Throwable) {
                        Toast.makeText(this@SearchListActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private val getResultPerson = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data

            if (person != data?.getStringExtra("person")) {
                person = data?.getStringExtra("person")

                mBinding!!.personBtn.setText(person + "명")

                var retrofit = Retrofit.Builder()
                    .baseUrl("http://13.125.85.98:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                var service = retrofit.create(Service::class.java)

                service.search(keyword, start_date, person!!.toInt(), end_date).enqueue(object :
                    Callback<List<inq_accommodation>> {
                    override fun onResponse(call: Call<List<inq_accommodation>>, response: Response<List<inq_accommodation>>) {
                        if(response.isSuccessful){
                            var results: List<inq_accommodation>? = response.body()

                            if (results != null) {
                                mBinding!!.resultLl.removeAllViews()
                                for (result in results) {
                                    val newCardViewBinding = ResultAccommodationItemBinding.inflate(layoutInflater)

                                    val url = result.photo

                                    Glide.with(this@SearchListActivity)
                                        .load(url) // 불러올 이미지 url
                                        .into(newCardViewBinding.resultImg1) // 이미지를 넣을 뷰

                                    newCardViewBinding.resultTitle1.text = result.name
                                    newCardViewBinding.resultRatingBar1.rating = result.star.toFloat()
                                    newCardViewBinding.resultRating1.text = result.star.toString()
                                    newCardViewBinding.resultPrice1.text = result.price.toString() + " 원"
                                    newCardViewBinding.resultTotal1.text = (result.price * person!!.toInt()).toString() + " 원"

                                    mBinding!!.resultLl.addView(newCardViewBinding.root)
                                }
                            }
                            else {
                                mBinding!!.resultLl.removeAllViews()
                            }
                        }
                        else {
                            Toast.makeText(this@SearchListActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<List<inq_accommodation>>, t: Throwable) {
                        Toast.makeText(this@SearchListActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySearchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.prevBtn.setOnClickListener {
            finish()
        }

        fitter1 = intent.getStringExtra("fitter 1")
        fitter2 = intent.getStringExtra("fitter 2")
        fitter3 = intent.getStringExtra("fitter 3")

        start_date = intent.getStringExtra("start_date")
        end_date = intent.getStringExtra("end_date")

        person = intent.getStringExtra("person")

        keyword = intent.getStringExtra("keyword")

        mBinding!!.dateBtn.setText(start_date + " ~ " + end_date)
        mBinding!!.personBtn.setText(person + "명")

        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.125.85.98:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(Service::class.java)

        service.search(keyword, start_date, person!!.toInt(), end_date).enqueue(object :
            Callback<List<inq_accommodation>> {
            override fun onResponse(call: Call<List<inq_accommodation>>, response: Response<List<inq_accommodation>>) {
                if(response.isSuccessful){
                    var results: List<inq_accommodation>? = response.body()

                    if (results != null) {
                        mBinding!!.defaultTv.visibility = View.GONE
                        for (result in results) {
                            val newCardViewBinding = ResultAccommodationItemBinding.inflate(layoutInflater)

                            val url = result.photo

                            Glide.with(this@SearchListActivity)
                                .load(url) // 불러올 이미지 url
                                .into(newCardViewBinding.resultImg1) // 이미지를 넣을 뷰

                            newCardViewBinding.resultTitle1.text = result.name
                            newCardViewBinding.resultRatingBar1.rating = result.star.toFloat()
                            newCardViewBinding.resultRating1.text = result.star.toString()
                            newCardViewBinding.resultPrice1.text = result.price.toString() + " 원"
                            newCardViewBinding.resultTotal1.text = (result.price * person!!.toInt()).toString() + " 원"

                            newCardViewBinding.root.setOnClickListener {
                                val intent = Intent(this@SearchListActivity, Tablayout_Activity::class.java)

                                startActivity(intent)
                            }

                            mBinding!!.resultLl.addView(newCardViewBinding.root)
                        }
                    }
                }
                else {
                    Toast.makeText(this@SearchListActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<inq_accommodation>>, t: Throwable) {
                Toast.makeText(this@SearchListActivity, "서버가 혼잡합니다. 잠시후 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show()
            }
        })

        mBinding!!.fitterBtn.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)

            if (fitter1 != null) {
                intent.putExtra("fitter 1", fitter1)
            }
            if (fitter2 != null) {
                intent.putExtra("fitter 2", fitter2)
            }
            if (fitter3 != null) {
                intent.putExtra("fitter 3", fitter3)
            }

            getResultFitter.launch(intent)
        }

        mBinding!!.dateBtn.setOnClickListener {
            val intent = Intent(this, DateChoseActivity::class.java)
            getResultDate.launch(intent)
        }

        mBinding!!.personBtn.setOnClickListener {
            val intent = Intent(this, PersonChoseActivity::class.java)
            getResultPerson.launch(intent)
        }

    }
}