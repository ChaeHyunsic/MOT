package com.example.mot

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityFitterBinding

class FitterActivity : AppCompatActivity(){

    private var mBinding: ActivityFitterBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityFitterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fitter1 = intent.getStringExtra("fitter 1")
        val fitter2 = intent.getStringExtra("fitter 2")
        val fitter3 = intent.getStringExtra("fitter 3")

        val buttons = listOf(mBinding!!.choseFitter1,mBinding!!.choseFitter2,mBinding!!.choseFitter3,mBinding!!.choseFitter4,
            mBinding!!.choseFitter5,mBinding!!.choseFitter6,mBinding!!.choseFitter7,mBinding!!.choseFitter8,mBinding!!.choseFitter9,
            mBinding!!.choseFitter10,mBinding!!.choseFitter11,)

        if (fitter1 != null) {
            mBinding!!.chosenFitter1.visibility = View.VISIBLE
            mBinding!!.chosenFitter1.setText(fitter1)

            for (button in buttons) {
                if(button.text == mBinding!!.chosenFitter1.text) {
                    button.setBackgroundResource(R.drawable.btn_background)
                }
            }
        }
        if (fitter2 != null) {
            mBinding!!.chosenFitter2.visibility = View.VISIBLE
            mBinding!!.chosenFitter2.setText(fitter2)

            for (button in buttons) {
                if(button.text == mBinding!!.chosenFitter2.text) {
                    button.setBackgroundResource(R.drawable.btn_background)
                }
            }
        }
        if (fitter3 != null) {
            mBinding!!.chosenFitter3.visibility = View.VISIBLE
            mBinding!!.chosenFitter3.setText(fitter3)

            for (button in buttons) {
                if(button.text == mBinding!!.chosenFitter3.text) {
                    button.setBackgroundResource(R.drawable.btn_background)
                }
            }
        }

        mBinding!!.prevBtn.setOnClickListener {
            val intent = Intent()
            if (mBinding!!.chosenFitter1.visibility == View.VISIBLE) {
                intent.putExtra("fitter 1", mBinding!!.chosenFitter1.text)
            }
            if (mBinding!!.chosenFitter2.visibility == View.VISIBLE) {
                intent.putExtra("fitter 2", mBinding!!.chosenFitter2.text)
            }
            if (mBinding!!.chosenFitter3.visibility == View.VISIBLE) {
                intent.putExtra("fitter 1", mBinding!!.chosenFitter3.text)
            }

            setResult(RESULT_OK, intent)
            finish()
        }

        mBinding!!.choseFitter1.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter1.text)
                mBinding!!.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter1.text)
                mBinding!!.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter1.text)
                mBinding!!.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.choseFitter2.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter2.text)
                mBinding!!.choseFitter2.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter2.text)
                mBinding!!.choseFitter2.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter2.text)
                mBinding!!.choseFitter2.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.choseFitter3.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter3.text)
                mBinding!!.choseFitter3.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter3.text)
                mBinding!!.choseFitter3.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter3.text)
                mBinding!!.choseFitter3.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.choseFitter4.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter4.text)
                mBinding!!.choseFitter4.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter4.text)
                mBinding!!.choseFitter4.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter4.text)
                mBinding!!.choseFitter4.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.choseFitter5.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter5.text)
                mBinding!!.choseFitter5.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter5.text)
                mBinding!!.choseFitter5.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter5.text)
                mBinding!!.choseFitter5.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.choseFitter6.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter6.text)
                mBinding!!.choseFitter6.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter6.text)
                mBinding!!.choseFitter6.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter6.text)
                mBinding!!.choseFitter6.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.choseFitter7.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter7.text)
                mBinding!!.choseFitter7.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter7.text)
                mBinding!!.choseFitter7.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter7.text)
                mBinding!!.choseFitter7.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.choseFitter8.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter8.text)
                mBinding!!.choseFitter8.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter8.text)
                mBinding!!.choseFitter8.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter8.text)
                mBinding!!.choseFitter8.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.choseFitter9.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter9.text)
                mBinding!!.choseFitter9.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter9.text)
                mBinding!!.choseFitter9.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter9.text)
                mBinding!!.choseFitter9.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.choseFitter10.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter10.text)
                mBinding!!.choseFitter10.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter10.text)
                mBinding!!.choseFitter10.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter10.text)
                mBinding!!.choseFitter10.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.choseFitter11.setOnClickListener{
            if (mBinding!!.chosenFitter1.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.VISIBLE
                mBinding!!.chosenFitter1.setText(mBinding!!.choseFitter11.text)
                mBinding!!.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter2.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.VISIBLE
                mBinding!!.chosenFitter2.setText(mBinding!!.choseFitter11.text)
                mBinding!!.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
            else if (mBinding!!.chosenFitter3.visibility == View.INVISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.VISIBLE
                mBinding!!.chosenFitter3.setText(mBinding!!.choseFitter11.text)
                mBinding!!.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
        }

        mBinding!!.chosenFitter1.setOnClickListener {
            if (mBinding!!.chosenFitter1.visibility == View.VISIBLE) {
                mBinding!!.chosenFitter1.visibility = View.INVISIBLE

                for (button in buttons) {
                    if(button.text == mBinding!!.chosenFitter1.text) {
                        button.setBackgroundResource(R.drawable.fitter_btn_background)
                    }
                }
            }
        }

        mBinding!!.chosenFitter2.setOnClickListener {
            if (mBinding!!.chosenFitter2.visibility == View.VISIBLE) {
                mBinding!!.chosenFitter2.visibility = View.INVISIBLE

                for (button in buttons) {
                    if(button.text == mBinding!!.chosenFitter2.text) {
                        button.setBackgroundResource(R.drawable.fitter_btn_background)
                    }
                }
            }
        }

        mBinding!!.chosenFitter3.setOnClickListener {
            if (mBinding!!.chosenFitter3.visibility == View.VISIBLE) {
                mBinding!!.chosenFitter3.visibility = View.INVISIBLE

                for (button in buttons) {
                    if(button.text == mBinding!!.chosenFitter3.text) {
                        button.setBackgroundResource(R.drawable.fitter_btn_background)
                    }
                }
            }
        }

    }
}