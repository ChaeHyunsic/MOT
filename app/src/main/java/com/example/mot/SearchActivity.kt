package com.example.mot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
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

    private lateinit var start_date: String
    private lateinit var end_date: String

    private lateinit var person: String

    private val getResultFitter = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            fitter1 = data?.getStringExtra("fitter 1").toString()
            fitter2 = data?.getStringExtra("fitter 2").toString()
            fitter3 = data?.getStringExtra("fitter 3").toString()
        }
    }

    private val getResultDate = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            start_date = data?.getStringExtra("start_date").toString()
            end_date = data?.getStringExtra("end_date").toString()

            mBinding!!.dateBtn.setText(start_date + " ~ " + end_date)
        }
    }

    private val getResultPerson = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            person = data?.getStringExtra("person").toString()

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

                    val intent = Intent(this@SearchActivity, SearchListActivity::class.java)

                    if (fitter1 != null) {
                        intent.putExtra("fitter 1", fitter1)
                    }
                    if (fitter2 != null) {
                        intent.putExtra("fitter 2", fitter2)
                    }
                    if (fitter3 != null) {
                        intent.putExtra("fitter 3", fitter3)
                    }

                    intent.putExtra("start_date", start_date)
                    intent.putExtra("end_date", end_date)

                    intent.putExtra("person", person)

                    intent.putExtra("keyword", query)

                    startActivity(intent)
                }
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