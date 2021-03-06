package com.kangdroid.k_labapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kangdroid.k_labapplication.data.dto.request.LoginRequest
import com.kangdroid.k_labapplication.databinding.LoginBinding
import com.kangdroid.k_labapplication.repository.ServerRepositoryImpl


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
                    Toast.makeText(this@LoginActivity,"Please fill out all forms.",Toast.LENGTH_SHORT).show()
                    flag = false
                }

                if(flag){
                    ServerRepositoryImpl.loginUser(
                        userLoginRequest = LoginRequest(userId, userPassword),
                        onSuccess = {
                            val intent = Intent(this@LoginActivity, TabActivity::class.java)
                            startActivity(intent)
                        },
                        onFailureLambda = {
                            Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
                        }
                    )
                }

            }

            joinBtn.setOnClickListener {
                val intent2 = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent2)
            }
        }
    }
}