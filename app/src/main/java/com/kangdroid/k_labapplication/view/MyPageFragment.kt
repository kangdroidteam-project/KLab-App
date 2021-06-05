package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kangdroid.k_labapplication.data.SealedUser
import com.kangdroid.k_labapplication.databinding.MyPageBinding
import com.kangdroid.k_labapplication.viewmodel.CommunityViewModel

class MyPageFragment : Fragment() {


    val viewModel: CommunityViewModel by viewModels()
    lateinit var binding : MyPageBinding
    lateinit var data : SealedUser


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = MyPageBinding.inflate(layoutInflater,container,false)

        init()
        viewModel.getUser()
        return binding.root
    }

    private fun init() {
        viewModel.liveSealedUser.observe(viewLifecycleOwner, Observer {
            data = it

            binding.apply {
                myName.text = data.userName
                myAddr.text = data.userAddress
                myPhone.text = data.userPhoneNumber
                myIntroduction.text = data.userIntroduction

                myClassJoin.setOnClickListener {
                    val intent = Intent(context, MyClassListActivity::class.java)
                    intent.putExtra("myPage",true)
                    startActivity(intent)
                }

                myClassOpen.setOnClickListener {
                    val intent = Intent(context,MyHostClassActivity::class.java)
                    intent.putExtra("myPage",false)
                    startActivity(intent)
                }
            }
        })

    }
}