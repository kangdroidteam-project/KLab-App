package com.kangdroid.k_labapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kangdroid.k_labapplication.databinding.OfflineadBinding
import com.kangdroid.k_labapplication.databinding.OnlineMarketBinding

class OfflineFragment : Fragment() {
    var binding: OfflineadBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OfflineadBinding.inflate(layoutInflater, container, false)

        //리사이클러뷰 설정
        binding?.apply {

        }
        return binding!!.root
    }


}