package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelResDateBinding
import java.util.*

class SelResDateActivity : AppCompatActivity() {

    private var mBinding: ActivitySelResDateBinding? = null
    private val binding get() = mBinding!!

    private var selectedStartDate: Long = 0
    private var selectedEndDate: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelResDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.prevBtn.setOnClickListener {
            finish()
        }

        mBinding!!.calendarView.date = System.currentTimeMillis()

        mBinding!!.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)

            if (mBinding!!.startDate.text == "") {
                selectedStartDate = selectedDate.timeInMillis
                mBinding!!.startDate.text = year.toString() + "년 " + (month+1).toString() + "월 " + dayOfMonth.toString() + "일"
            }
            else {
                if (selectedDate.timeInMillis > selectedStartDate) {
                    selectedEndDate = selectedDate.timeInMillis
                    mBinding!!.endDate.text = year.toString() + "년 " + (month+1).toString() + "월 " + dayOfMonth.toString() + "일"
                    mBinding!!.confirmBtn.visibility = View.VISIBLE
                }
                else {
                    mBinding!!.wrongAnno1.visibility = View.VISIBLE
                    mBinding!!.wrongAnno2.visibility = View.VISIBLE
                    mBinding!!.startDate.text = ""
                    selectedStartDate = 0
                    mBinding!!.endDate.text = ""
                    selectedEndDate = 0
                }
            }
        }

        mBinding!!.confirmBtn.setOnClickListener {
            if (mBinding!!.confirmBtn.visibility == View.VISIBLE) {
                val intent = Intent(this, SelResResultActivity::class.java)

                intent.putExtra("start_date", mBinding!!.startDate.text)
                intent.putExtra("end_date", mBinding!!.endDate.text)

                startActivity(intent)
            }
        }

    }
}