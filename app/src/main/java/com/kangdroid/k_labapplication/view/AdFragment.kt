package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kangdroid.k_labapplication.adapter.ClassListAdapter
import com.kangdroid.k_labapplication.databinding.ClassListBinding
import com.kangdroid.k_labapplication.databinding.OnlineMarketBinding
import com.kangdroid.k_labapplication.viewmodel.CommunityViewModel

class AdFragment : Fragment() {
    var binding: OnlineMarketBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OnlineMarketBinding.inflate(layoutInflater, container, false)

        //리사이클러뷰 설정
        binding?.apply {

        }
        return binding!!.root
    }


}