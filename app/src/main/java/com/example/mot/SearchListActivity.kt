package com.example.mot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySearchListBinding

class SearchListActivity : AppCompatActivity() {

    private var mBinding: ActivitySearchListBinding? = null
    private val binding get() = mBinding!!

    private var fitter1: String? = null
    private var fitter2: String? = null
    private var fitter3: String? = null

    private var start_date: String? = null
    private var end_date: String? = null

    private var person: String? = null

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
            start_date = data?.getStringExtra("start_date")
            end_date = data?.getStringExtra("end_date")

            mBinding!!.dateBtn.setText(start_date + "~" + end_date)
        }
    }

    private val getResultPerson = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            person = data?.getStringExtra("person")

            mBinding!!.personBtn.setText(person + "명")
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

        mBinding!!.dateBtn.setText(start_date + "~" + end_date)
        mBinding!!.personBtn.setText(person + "명")

        mBinding!!.fitterBtn.setOnClickListener {
            val intent = Intent(this, FitterActivity::class.java)

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