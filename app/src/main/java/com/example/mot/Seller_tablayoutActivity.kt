package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mot.databinding.ActivitySellerTablayoutBinding
import com.google.android.material.tabs.TabLayout

class Seller_tablayoutActivity: AppCompatActivity() {

    lateinit var binding: ActivitySellerTablayoutBinding
    lateinit var tab1: Seller_packageinfo_Fragment
    lateinit var tab2: Seller_packageinfo_detailsActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellerTablayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tab1 = Seller_packageinfo_Fragment()

        binding.backBtn.setOnClickListener {
            finish()
        }


        supportFragmentManager.beginTransaction().add(R.id.seller_tab_fl, tab1).commit()

        binding.sellerTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        replaceView(tab1)
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    private fun replaceView(tab: Fragment) {
        var selectorFragment: Fragment? = null
        selectorFragment = tab
        selectorFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.seller_tab_fl, it).commit()
        }
    }
}