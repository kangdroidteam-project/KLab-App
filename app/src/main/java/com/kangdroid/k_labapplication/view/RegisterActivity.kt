package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kangdroid.k_labapplication.data.dto.request.RegisterRequest
import com.kangdroid.k_labapplication.databinding.LoginBinding
import com.kangdroid.k_labapplication.databinding.RegisterBinding
import com.kangdroid.k_labapplication.repository.ServerRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class RegisterActivity : AppCompatActivity() {
    val binding2 : LoginBinding by lazy{
        LoginBinding.inflate(layoutInflater)
    }

    private val binding : RegisterBinding by lazy {
        RegisterBinding.inflate(layoutInflater)
    }

    var flag : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()

    }

    private fun init(){

        binding.apply {


            joinBtn.setOnClickListener {

                val userId =  joinId.text.toString()
                val userName = joinName.text.toString()
                val userAddress = joinAddr.text.toString()
                val userPhoneNumber = joinPhone.text.toString()
                val userPassword = joinPw.text.toString()

                val pwcheck = joinPwCheck.text.toString()

                if(userId=="" || userName=="" || userAddress=="" || userPhoneNumber=="" || userPassword==""){
                    Toast.makeText(this@RegisterActivity,"양식을 모두 채우지 않았습니다.",Toast.LENGTH_SHORT).show()
                    flag = false
                }

                else if(userPassword != pwcheck){
                    pwchecklayout.error = "비밀번호가 서로 맞지 않습니다."
                    flag= false
                }

                else if(!checkbox.isChecked){
                    Toast.makeText(this@RegisterActivity,"약관 동의를 하지 않았습니다.",Toast.LENGTH_SHORT).show()
                    flag = false
                }

                if(flag){
                    ServerRepositoryImpl.registerUser(RegisterRequest(userId, userName, userAddress, userPhoneNumber, userPassword),
                        onSuccess = {
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            startActivity(intent)
                        },
                        onFailureLambda = {
                            Toast.makeText(this@RegisterActivity, it, Toast.LENGTH_SHORT).show()
                        }
                    )
                }

            }
        }

    }
}