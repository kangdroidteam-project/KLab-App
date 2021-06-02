package com.kangdroid.k_labapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kangdroid.k_labapplication.databinding.FragmentInfoBinding

class InfoFragment(val num:Int) : Fragment() {
    var binding: FragmentInfoBinding? = null
    val list = arrayListOf<Int>(R.drawable.info0,R.drawable.info1, R.drawable.info2, R.drawable.info3, R.drawable.info4, R.drawable.info5)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater, container, false)

        binding?.apply {
            img?.setImageResource(list[num])
            if(num == 5){
                img.setOnClickListener { 
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        return binding!!.root
    }
}