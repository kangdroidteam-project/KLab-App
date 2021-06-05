package com.kangdroid.k_labapplication.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kangdroid.k_labapplication.adapter.MyClassListAdapter
import com.kangdroid.k_labapplication.data.SimplifiedMyPageCommunity
import com.kangdroid.k_labapplication.databinding.ClassmatchBinding
import com.kangdroid.k_labapplication.viewmodel.CommunityViewModel

class MatchingActivity : AppCompatActivity(){

//    val viewModel: CommunityViewModel by viewModels()

    private val binding: ClassmatchBinding by lazy {
        ClassmatchBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    fun init(){
        binding.recyelrview.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

    }
}