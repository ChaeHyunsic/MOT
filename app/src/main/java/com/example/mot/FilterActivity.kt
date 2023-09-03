package com.example.mot

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityFilterBinding

class FilterActivity : AppCompatActivity(){

    lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fitter1 = intent.getStringExtra("fitter 1")
        val fitter2 = intent.getStringExtra("fitter 2")
        val fitter3 = intent.getStringExtra("fitter 3")

        val buttons = listOf(binding.choseFitter1,binding.choseFitter2,binding.choseFitter3,binding.choseFitter4,
            binding.choseFitter5,binding.choseFitter6,binding.choseFitter7,binding.choseFitter8,binding.choseFitter9,
            binding.choseFitter10,binding.choseFitter11)

        if (fitter1 != null) {
            binding.chosenFitter1.visibility = View.VISIBLE
            binding.chosenFitter1.setText(fitter1)

            for (button in buttons) {
                if(button.text == binding.chosenFitter1.text) {
                    button.setBackgroundResource(R.drawable.btn_background)
                }
            }
        }
        if (fitter2 != null) {
            binding.chosenFitter2.visibility = View.VISIBLE
            binding.chosenFitter2.setText(fitter2)

            for (button in buttons) {
                if(button.text == binding.chosenFitter2.text) {
                    button.setBackgroundResource(R.drawable.btn_background)
                }
            }
        }
        if (fitter3 != null) {
            binding.chosenFitter3.visibility = View.VISIBLE
            binding.chosenFitter3.setText(fitter3)

            for (button in buttons) {
                if(button.text == binding.chosenFitter3.text) {
                    button.setBackgroundResource(R.drawable.btn_background)
                }
            }
        }

        binding.backIv.setOnClickListener {
            if (binding.chosenFitter1.visibility == View.VISIBLE) {
                val text = binding.chosenFitter1.text.toString()
                intent.putExtra("filter1", text)
            }
            if (binding.chosenFitter2.visibility == View.VISIBLE) {
                val text = binding.chosenFitter2.text.toString()
                intent.putExtra("filter2", text)
            }
            if (binding.chosenFitter3.visibility == View.VISIBLE) {
                val text = binding.chosenFitter3.text.toString()
                intent.putExtra("filter3", text)
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.choseFitter1.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter1.text)
                binding.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter1.text)
                binding.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter1.text)
                binding.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.choseFitter2.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter2.text)
                binding.choseFitter2.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter2.text)
                binding.choseFitter2.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter2.text)
                binding.choseFitter2.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.choseFitter3.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter3.text)
                binding.choseFitter3.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter3.text)
                binding.choseFitter3.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter3.text)
                binding.choseFitter3.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.choseFitter4.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter4.text)
                binding.choseFitter4.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter4.text)
                binding.choseFitter4.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter4.text)
                binding.choseFitter4.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.choseFitter5.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter5.text)
                binding.choseFitter5.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter5.text)
                binding.choseFitter5.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter5.text)
                binding.choseFitter5.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.choseFitter6.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter6.text)
                binding.choseFitter6.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter6.text)
                binding.choseFitter6.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter6.text)
                binding.choseFitter6.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.choseFitter7.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter7.text)
                binding.choseFitter7.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter7.text)
                binding.choseFitter7.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter7.text)
                binding.choseFitter7.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.choseFitter8.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter8.text)
                binding.choseFitter8.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter8.text)
                binding.choseFitter8.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter8.text)
                binding.choseFitter8.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.choseFitter9.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter9.text)
                binding.choseFitter9.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter9.text)
                binding.choseFitter9.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter9.text)
                binding.choseFitter9.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.choseFitter10.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter10.text)
                binding.choseFitter10.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter10.text)
                binding.choseFitter10.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter10.text)
                binding.choseFitter10.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.choseFitter11.setOnClickListener{
            if (binding.chosenFitter1.visibility == View.INVISIBLE) {
                binding.chosenFitter1.visibility = View.VISIBLE
                binding.chosenFitter1.setText(binding.choseFitter11.text)
                binding.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter2.visibility == View.INVISIBLE) {
                binding.chosenFitter2.visibility = View.VISIBLE
                binding.chosenFitter2.setText(binding.choseFitter11.text)
                binding.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
            else if (binding.chosenFitter3.visibility == View.INVISIBLE) {
                binding.chosenFitter3.visibility = View.VISIBLE
                binding.chosenFitter3.setText(binding.choseFitter11.text)
                binding.choseFitter1.setBackgroundResource(R.drawable.btn_background)
            }
        }

        binding.chosenFitter1.setOnClickListener {
            if (binding.chosenFitter1.visibility == View.VISIBLE) {
                binding.chosenFitter1.visibility = View.INVISIBLE

                for (button in buttons) {
                    if(button.text == binding.chosenFitter1.text) {
                        button.setBackgroundResource(R.drawable.fitter_btn_background)
                    }
                }
            }
        }

        binding.chosenFitter2.setOnClickListener {
            if (binding.chosenFitter2.visibility == View.VISIBLE) {
                binding.chosenFitter2.visibility = View.INVISIBLE

                for (button in buttons) {
                    if(button.text == binding.chosenFitter2.text) {
                        button.setBackgroundResource(R.drawable.fitter_btn_background)
                    }
                }
            }
        }

        binding.chosenFitter3.setOnClickListener {
            if (binding.chosenFitter3.visibility == View.VISIBLE) {
                binding.chosenFitter3.visibility = View.INVISIBLE

                for (button in buttons) {
                    if(button.text == binding.chosenFitter3.text) {
                        button.setBackgroundResource(R.drawable.fitter_btn_background)
                    }
                }
            }
        }

    }
}