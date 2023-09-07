package com.example.mot

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mot.databinding.ActivityAccommodationBinding
import com.google.android.material.tabs.TabLayout

class AccommodationActivity : AppCompatActivity() {

    private var mBinding: ActivityAccommodationBinding? = null
    private val binding get() = mBinding!!
    lateinit var tab1: PackageinfoFragment
    lateinit var tab2: RoominfoFragment
    lateinit var tab3: ReviewFragment
    lateinit var tab4: LocationFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityAccommodationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mBinding!!.shoppingBasket.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

        }

        mBinding!!.yellowBar.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

        }
        tab1 = PackageinfoFragment()
        tab2 = RoominfoFragment()
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
            .replace(R.id.tab_fl, fragment)
            .commit()
    }


}