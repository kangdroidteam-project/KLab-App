package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
    var flag = true
    lateinit var data : List<SimplifiedMyPageCommunity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init(){

        if(intent.hasExtra("id")){
            id = intent.getLongExtra("id",-1)
        }

        binding.classListRV.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        if(id!=0L){

            viewModel.liveMyPageCommunityList.observe(this, Observer {
                
            adapter = MyClassListAdapter(it)

            adapter.itemClickListener = object : MyClassListAdapter.onItemClickListener{
                override fun onItemClick(
                    holder: MyClassListAdapter.ViewHolder,
                    view: View,
                    dataSimplified: SimplifiedMyPageCommunity,
                    position: Int
                ) {
                    val intent = Intent(this@MyClassListActivity,ClassDetailActivity::class.java)
                    intent.putExtra("id",dataSimplified.id)
                    startActivity(intent)
                }

            }
            binding.classListRV.adapter = adapter
        })
        viewModel.registerClass(id)
    }
                
    else if(flag){

            viewModel.liveMyPageCommunityList.observe(this, Observer {

                adapter = MyClassListAdapter(it)

                adapter.itemClickListener = object : MyClassListAdapter.onItemClickListener{
                    override fun onItemClick(
                        holder: MyClassListAdapter.ViewHolder,
                        view: View,
                        dataSimplified: SimplifiedMyPageCommunity,
                        position: Int
                    ) {
                        val intent = Intent(this@MyClassListActivity,ClassDetailActivity::class.java)
                        intent.putExtra("id",dataSimplified.id)
                        intent.putExtra("flag","true")
                        startActivity(intent)
                    }

                }
                binding.classListRV.adapter = adapter
            })
            viewModel.getUserParticipatedClass(flag)

        }
    }
}