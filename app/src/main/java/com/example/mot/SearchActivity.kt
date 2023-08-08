package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySearchBinding


class SearchActivity : AppCompatActivity() {

    private var mBinding: ActivitySearchBinding? = null
    private val binding get() = mBinding!!
    private lateinit var recentSearches: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.prevBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        mBinding!!.fitterBtn.setOnClickListener {
            val intent = Intent(this, FitterActivity::class.java)
            startActivity(intent)
        }

        recentSearches = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, recentSearches)
        mBinding!!.historyLv.adapter = adapter

        mBinding!!.searchView.setOnClickListener {
            val query = mBinding!!.searchView.query.toString()
            if (query.isNotEmpty()) {
                recentSearches.add(0, query)
                adapter.notifyDataSetChanged()
                mBinding!!.searchView.setQuery("", false)
            }

            val intent = Intent(this, SearchListActivity::class.java)
            startActivity(intent)
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
            startActivity(intent)
        }

        mBinding!!.personBtn.setOnClickListener {
            val intent = Intent(this, PersonChoseActivity::class.java)
            startActivity(intent)
        }

    }
}