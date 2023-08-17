package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivitySelJoinAddressDetailBinding

class SelJoinAddressDetailActivity : AppCompatActivity() {

    private var mBinding: ActivitySelJoinAddressDetailBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySelJoinAddressDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mBinding!!.dupliBtn.setOnClickListener {
            if (mBinding!!.dupliBtn.text.toString() != null) {

                for (i in mBinding!!.resultTl.childCount - 1 downTo 1) {
                    val childView = mBinding!!.resultTl.getChildAt(i)
                    if (childView is TableRow) {
                        mBinding!!.resultTl.removeViewAt(i)
                    }
                }

                val row = TableRow(this)

                val number_tv = TextView(this)
                number_tv.text = "06656"

                val address_tv = TextView(this)
                address_tv.text = "서울특별시 서초구 반포대로 23길 6 (서초동,효진빌딩)"

                row.addView(number_tv)
                row.addView(address_tv)

                row.setOnClickListener {
                    val intent = Intent()

                    intent.putExtra("accom_address", address_tv.text)

                    setResult(RESULT_OK, intent)
                    finish()
                }

                mBinding!!.resultTl.addView(row)
            }
            else {
                Toast.makeText(this,"주소가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }



    }
}