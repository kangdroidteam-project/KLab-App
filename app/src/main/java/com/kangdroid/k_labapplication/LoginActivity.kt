package com.kangdroid.k_labapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kangdroid.k_labapplication.databinding.LoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding: LoginBinding by lazy {
        LoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.apply {
            var userId = loginId.text.toString()
            var userPassword = loginPw.text.toString()
        }

    }
}