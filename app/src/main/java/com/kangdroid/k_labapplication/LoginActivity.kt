package com.kangdroid.k_labapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kangdroid.k_labapplication.data.dto.request.LoginRequest
import com.kangdroid.k_labapplication.data.dto.request.RegisterRequest
import com.kangdroid.k_labapplication.databinding.ClassListBinding
import com.kangdroid.k_labapplication.databinding.LoginBinding
import com.kangdroid.k_labapplication.repository.ServerRepository
import com.kangdroid.k_labapplication.repository.ServerRepositoryImpl
import java.lang.RuntimeException

class LoginActivity : AppCompatActivity() {
    private val binding: LoginBinding by lazy {
        LoginBinding.inflate(layoutInflater)
    }


    var flag : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        binding.loginBtn
        binding.apply {

            loginBtn.setOnClickListener {
                var userId = loginId.text.toString()
                var userPassword = loginPw.text.toString()

                if (userId=="" || userPassword==""){
                    Toast.makeText(this@LoginActivity,"양식을 모두 채우지 않았습니다.",Toast.LENGTH_SHORT).show()
                    flag = false
                }

                if(flag){
                    try{
                        ServerRepositoryImpl.loginUser(LoginRequest(userId, userPassword))
                        val intent = Intent(this@LoginActivity, ClassListActivity::class.java)
                        startActivity(intent)
                    }catch (e:RuntimeException){
                        //error
                    }
                }

            }

            joinBtn.setOnClickListener {
                val intent2 = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent2)
            }
        }
    }
}