package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mot.databinding.ActivitySelTabBinding
import com.google.android.material.tabs.TabLayout

class SelTabActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelTabBinding

    lateinit var tab3: SelReviewListFragment
    lateinit var tab4: SelLocationFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivitySelTabBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        tab3 = SelReviewListFragment()
        tab4 = SelLocationFragment()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        replaceView(tab3)
                    }
                    1 -> {
                        replaceView(tab3)
                    }
                    2 -> {
                        replaceView(tab3)
                    }
                    3 -> {
                        replaceView(tab4)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun replaceView(tab: Fragment){
        var selectorFragment: Fragment? = null
        selectorFragment = tab
        selectorFragment?.let{
            supportFragmentManager.beginTransaction()
                .replace(R.id.tab_frameLayout, it).commit()
        }
    }
}