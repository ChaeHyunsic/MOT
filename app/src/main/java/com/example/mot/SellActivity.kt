package com.example.mot

import android.content.Intent
import android.icu.text.IDNA.Info
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mot.databinding.ActivitySellBinding
import com.google.android.material.tabs.TabLayout

class SellActivity: AppCompatActivity() {

    lateinit var binding: ActivitySellBinding

    lateinit var tab2: RoomInfoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySellBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tab2 = RoomInfoFragment()

        supportFragmentManager.beginTransaction().add(R.id.sell_fl, tab2).commit()

        binding.sellTl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        replaceView(tab2)
                    }

                    1 -> {
                        replaceView(tab2)
                    }

                    2 -> {
                        replaceView(tab2)
                    }

                    3 -> {
                        replaceView(tab2)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        binding.threeDotIv.setOnClickListener {
            var popupMenu = PopupMenu(applicationContext, it)

            menuInflater?.inflate(R.menu.menu_sell, popupMenu.menu)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.account -> {
                        val intent = Intent(this, MyAccountActivity::class.java)
                        startActivity(intent)
                        return@setOnMenuItemClickListener true
                    }
                    else -> {
                        return@setOnMenuItemClickListener true
                    }
                }
            }
        }

        binding.basicModifyTv.setOnClickListener {
            var intent = Intent(this, InfoActivity::class.java)
            startForResult.launch(intent)
        }

        binding.filterModifyTv.setOnClickListener {
            var intent = Intent(this, FilterActivity::class.java)
            startForResult.launch(intent)
        }

    }

    private fun replaceView(tab: Fragment){
        var selectorFragment: Fragment? = null
        selectorFragment = tab
        selectorFragment?.let{
            supportFragmentManager.beginTransaction()
                .replace(R.id.sell_fl, it).commit()
        }
    }

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->

        if (result.resultCode == RESULT_OK) {
            var info_text = result.data?.getStringExtra("info")
            binding.basicInfoTv.text = info_text

            if(result.data?.getStringExtra("filter1") != null){
                binding.filter1Iv.visibility = View.VISIBLE
                binding.filter1InfoTv.visibility = View.VISIBLE
                binding.filter1InfoTv.text = result.data?.getStringExtra("filter1")
            }
            if(result.data?.getStringExtra("filter2") != null){
                binding.filter2Iv.visibility = View.VISIBLE
                binding.filter2InfoTv.visibility = View.VISIBLE
                binding.filter2InfoTv.text = result.data?.getStringExtra("filter2")
            }
            if(result.data?.getStringExtra("filter3") != null){
                binding.filter3Iv.visibility = View.VISIBLE
                binding.filter3InfoTv.visibility = View.VISIBLE
                binding.filter3InfoTv.text = result.data?.getStringExtra("filter3")
            }
        }
        else{

        }
    }

}