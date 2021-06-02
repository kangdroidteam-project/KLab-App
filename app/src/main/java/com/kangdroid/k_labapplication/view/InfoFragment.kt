package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kangdroid.k_labapplication.R
import com.kangdroid.k_labapplication.databinding.FragmentInfoBinding

class InfoFragment(val num:Int) : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding: FragmentInfoBinding get() = _binding!!
    private val list = arrayListOf<Int>(
        R.drawable.info0,
        R.drawable.info1,
        R.drawable.info2,
        R.drawable.info3,
        R.drawable.info4,
        R.drawable.info5
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            img.setImageResource(list[num])
            img.setOnClickListener {
                if(num == 5){
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}