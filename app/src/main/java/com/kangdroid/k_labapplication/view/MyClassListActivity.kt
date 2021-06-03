package com.kangdroid.k_labapplication.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.kangdroid.k_labapplication.adapter.MyClassListAdapter
import com.kangdroid.k_labapplication.data.SimplifiedMyPageCommunity
import com.kangdroid.k_labapplication.databinding.MyClassListBinding
import com.kangdroid.k_labapplication.viewmodel.CommunityViewModel


class MyClassListActivity : AppCompatActivity() {

    val viewModel: CommunityViewModel by viewModels()
    lateinit var adapter : MyClassListAdapter

    private val binding : MyClassListBinding by lazy{
        MyClassListBinding.inflate(layoutInflater)
    }

    var id : Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init(){

        if(intent.hasExtra("id")){
            val id = intent.getLongExtra("id",-1)
        }
        if(id==-1L)return

        binding.classListRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        viewModel.registerClass(id)
        adapter = MyClassListAdapter(viewModel.liveMyPageCommunityList)

        adapter.itemClickListener = object : MyClassListAdapter.onItemClickListener{
            override fun onItemClick(
                holder: MyClassListAdapter.ViewHolder,
                view: View,
                dataSimplified: SimplifiedMyPageCommunity,
                position: Int
            ) {
                // 클래스 상세내용으로 전환
            }

        }
        binding.classListRV.adapter = adapter

    }
}