package com.kangdroid.k_labapplication.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kangdroid.k_labapplication.adapter.MyClassListAdapter
import com.kangdroid.k_labapplication.databinding.MyClassListBinding

class MyClassListActivity : AppCompatActivity() {
    
    lateinit var adapter : MyClassListAdapter

    private val binding : MyClassListBinding by lazy{
        MyClassListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init(){

        binding.classListRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        adapter = MyClassListAdapter(ArrayList<SimplifiedMyPageCommunity>())

        //adapter.items.add 할 곳

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