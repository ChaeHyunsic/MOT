package com.example.mot

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mot.databinding.ActivityMypageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MypageActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMypageBinding
    private lateinit var EditNickname : EditText
    private lateinit var Nickname : TextView

    private var retrofit = Retrofit.Builder()
        .baseUrl("http://13.125.85.98:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var service = retrofit.create(Service::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMypageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // activity 전환
        binding.btnAllareaSelect.setOnClickListener {
            val intent = Intent(this, AllareaActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        binding.btnAroundSelect.setOnClickListener {
            val intent = Intent(this, MyaroundActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        binding.btnMainSelect.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        binding.btnMypickSelect.setOnClickListener {
            val intent = Intent(this, MypickActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        // 닉네임 조회

        // 닉네임 변경
        Nickname = binding.profile
        binding.profile.setOnClickListener(this)

        EditNickname= binding.profileEdit
        EditNickname.setOnClickListener {
            val text : String = binding.profileEdit.text.toString()

            service.nicknameChange(TokenManager.getAuthToken(), text).enqueue(object : Callback<NicknameData>{
                override fun onResponse(
                    call: Call<NicknameData>,
                    response: Response<NicknameData>
                ) {
                    if(response.isSuccessful){
                        val result = response.body()

                        if(result != null){
                            setEditName(false)
                        }
                    }
                }

                override fun onFailure(call: Call<NicknameData>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }

        // mypage내에서 화면 전환
        binding.question.setOnClickListener {
            val intent = Intent(this, ManagerActivity::class.java)
            startActivity(intent)
        }

        binding.reservation.setOnClickListener {
            val intent = Intent(this,ReserveUseActivity::class.java)
            startActivity(intent)
        }

        binding.myLodging.setOnClickListener {
            val intent = Intent(this, MypickActivity::class.java)
            startActivity(intent)
        }

        binding.myReview.setOnClickListener {
            val intent = Intent(this,MyreviewActivity::class.java)
            startActivity(intent)
        }

        binding.profileEdit.setOnEditorActionListener{ textView, action, event ->
            var handled = false

            if (action == EditorInfo.IME_ACTION_DONE) {
                // 키보드 내리기
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.profileEdit.windowToken, 0)
                handled = true
            }

            handled
        }
    }

    // 닉네임 변경
    private fun setEditName(isAvailable: Boolean){
        if(isAvailable){
            EditNickname.visibility = View.VISIBLE
            Nickname.visibility = View.INVISIBLE

        }
        else{
            EditNickname.visibility = View.INVISIBLE
            Nickname.visibility = View.VISIBLE

            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.profile.windowToken, 0)

            val txt : String = EditNickname.text.toString()
            Nickname.text = txt
        }
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.profile -> {
                setEditName(isAvailable = true)
            }
            R.id.profile_edit -> {
                setEditName(isAvailable = false)
            }
        }
        true
    }
}