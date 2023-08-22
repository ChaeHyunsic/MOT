package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityDateChoseBinding
import java.text.SimpleDateFormat
import java.util.*

class DateChoseActivity : AppCompatActivity() {

    private var mBinding: ActivityDateChoseBinding? = null
    private val binding get() = mBinding!!

    private var selectedStartDate: Long = 0
    private var selectedEndDate: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityDateChoseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.prevBtn.setOnClickListener {
            finish()
        }

        mBinding!!.calendarView.date = System.currentTimeMillis()

        mBinding!!.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            var sdf = SimpleDateFormat("yyyy-MM-dd")

            if (mBinding!!.startDate.text == "") {
                selectedStartDate = selectedDate.timeInMillis
                mBinding!!.startDate.text = sdf.format(selectedDate.time)
            }
            else {
                if (selectedDate.timeInMillis > selectedStartDate) {
                    selectedEndDate = selectedDate.timeInMillis
                    mBinding!!.endDate.text = sdf.format(selectedDate.time)
                    mBinding!!.choseBtn.visibility = View.VISIBLE
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

        mBinding!!.choseBtn.setOnClickListener {
            if (mBinding!!.choseBtn.visibility == View.VISIBLE) {
                val intent = Intent()
                intent.putExtra("start_date", mBinding!!.startDate.text)
                intent.putExtra("end_date", mBinding!!.endDate.text)

                setResult(RESULT_OK, intent)
                finish()
            }
        }

    }
}