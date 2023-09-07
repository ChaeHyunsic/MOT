package com.example.mot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mot.databinding.ActivityTablayoutDetailsBinding
import com.google.android.material.tabs.TabLayout

class Tablayout_details_Activity    : AppCompatActivity() {

        private var mBinding: ActivityTablayoutDetailsBinding? = null
        private val binding: ActivityTablayoutDetailsBinding get() = mBinding!!
        lateinit var tab1: Packageinfo_detailsFragment
        lateinit var tab2: InqRoominfoDetailsFragment
        lateinit var tab3: ReviewFragment
        lateinit var tab4: LocationFragment

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            mBinding = ActivityTablayoutDetailsBinding.inflate(layoutInflater)
            setContentView(binding.root)

            mBinding!!.yellowBar.setOnClickListener {
                val intent =  Intent(this,PaymentActivity::class.java)
                startActivity(intent)
            }
            mBinding!!.backBtn.setOnClickListener {
                val intent =  Intent(this,Tablayout_Activity::class.java)
                startActivity(intent)
                finish()
            }
            mBinding!!.shoppingBasket.setOnClickListener {
                val intent = Intent(this, PaymentActivity::class.java)
                startActivity(intent)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

            }

            tab1 = Packageinfo_detailsFragment()
            tab2 = InqRoominfoDetailsFragment()
            tab3 = ReviewFragment()
            tab4 = LocationFragment()

            supportFragmentManager.beginTransaction().add(R.id.tab_fl, tab1).commit()

            binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        0 -> {
                            replaceView(tab1)
                        }
                        1 -> {
                            replaceView(tab2)
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
                .replace(R.id.tab_fl, it).commit()
        }
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.tab_fl,fragment)
            .commit()
    }

}