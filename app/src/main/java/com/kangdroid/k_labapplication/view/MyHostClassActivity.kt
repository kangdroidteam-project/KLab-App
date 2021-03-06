package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kangdroid.k_labapplication.adapter.MyClassListAdapter
import com.kangdroid.k_labapplication.adapter.MyHostClassAdapter
import com.kangdroid.k_labapplication.data.SimplifiedMyPageCommunity
import com.kangdroid.k_labapplication.databinding.MyClassListBinding
import com.kangdroid.k_labapplication.databinding.MyHostClassListBinding
import com.kangdroid.k_labapplication.viewmodel.CommunityViewModel

class MyHostClassActivity : AppCompatActivity() {

    val viewModel: CommunityViewModel by viewModels()
    lateinit var adapter : MyHostClassAdapter
    var flag = false

    private val binding : MyHostClassListBinding by lazy{
        MyHostClassListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    fun init(){

        binding.classListRV.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

        if(!flag){
            Log.e("MYHOSTCLASS","FLAG is $flag")
            viewModel.liveMyPageCommunityList.observe(this, Observer {

                adapter = MyHostClassAdapter(it)

                adapter.itemClickListener = object : MyHostClassAdapter.onItemClickListener{
                    override fun onItemClick(
                        holder: MyHostClassAdapter.ViewHolder,
                        view: View,
                        dataSimplified: SimplifiedMyPageCommunity,
                        position: Int
                    ) {
                        val intent = Intent(this@MyHostClassActivity,MatchingActivity::class.java)
                        intent.putExtra("matching",dataSimplified.id)
                        startActivity(intent)
                    }

                }
                binding.classListRV.adapter = adapter
            })
            viewModel.getUserParticipatedClass(flag)
        }
    }
}