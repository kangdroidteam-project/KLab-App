package com.kangdroid.k_labapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.kangdroid.k_labapplication.databinding.ClassListBinding
import com.kangdroid.k_labapplication.viewmodel.CommunityViewModel

class ClassListActivity : AppCompatActivity() {
    val viewModel: CommunityViewModel by viewModels()

    private val binding: ClassListBinding by lazy {
        ClassListBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.liveSimpleClassList.observe(this) {
            Log.d("ClassList", "아직 안됨")
        }
    }

    fun init() {
        // Class List 받아오는 부분에
        viewModel.getClassList()
    }
}