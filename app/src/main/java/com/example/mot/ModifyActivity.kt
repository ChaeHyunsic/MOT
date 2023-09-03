package com.example.mot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mot.R
import com.example.mot.databinding.ActivityModifyBinding
import com.example.register.ModifyPwFragment
import com.google.android.material.tabs.TabLayout

class ModifyActivity: AppCompatActivity() {

    lateinit var binding: ActivityModifyBinding
    lateinit var tab1: ModifyIdFragment
    lateinit var tab2: ModifyPwFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tab1 = ModifyIdFragment()
        tab2 = ModifyPwFragment()


        supportFragmentManager.beginTransaction().add(R.id.modify_fl, tab1).commit()

        binding.modifyTl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        replaceView(tab1)
                    }
                    1 -> {
                        replaceView(tab2)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.backIv.setOnClickListener{
            finish()
        }

    }

    private fun replaceView(tab: Fragment){
        var selectorFragment: Fragment? = null
        selectorFragment = tab
        selectorFragment?.let{
            supportFragmentManager.beginTransaction()
                .replace(R.id.modify_fl, it).commit()
        }
    }

    fun changeFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.modify_fl, fragment)
            .commit()
    }
}