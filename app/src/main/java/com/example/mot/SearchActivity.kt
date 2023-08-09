package com.example.mot

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySearchBinding


class SearchActivity : AppCompatActivity() {

    private var mBinding: ActivitySearchBinding? = null
    private val binding get() = mBinding!!
    private lateinit var recentSearches: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>

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

        mBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.prevBtn.setOnClickListener {
            finish()
        }

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

        recentSearches = mutableListOf()
        adapter =
            ArrayAdapter(this, R.layout.activity_recent_search_item, R.id.search_tv, recentSearches)
        mBinding!!.historyLv.adapter = adapter

        mBinding!!.searchView.setOnClickListener {
            if (start_date != null && person != null) {
                val query = mBinding!!.searchView.query.toString()
                if (query.isNotEmpty()) {
                    recentSearches.add(0, query)
                    adapter.notifyDataSetChanged()
                    mBinding!!.searchView.setQuery("", false)
                }

                val intent = Intent(this, SearchListActivity::class.java)
                intent.putExtra("fitter 1", fitter1)
                intent.putExtra("fitter 2", fitter2)
                intent.putExtra("fitter 3", fitter3)

                intent.putExtra("start_date", start_date)
                intent.putExtra("end_date", end_date)

                intent.putExtra("person", person)

                startActivity(intent)
            }
            else {
                Toast.makeText(this, "입력되지 않은 옵션이 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        mBinding!!.delBtn.setOnClickListener {
            recentSearches.clear()
            adapter.notifyDataSetChanged()
        }

        mBinding!!.historyLv.setOnItemClickListener { _, _, position, _ ->
            val selectedKeyword = recentSearches[position]
            mBinding!!.searchView.setQuery(selectedKeyword, true)
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