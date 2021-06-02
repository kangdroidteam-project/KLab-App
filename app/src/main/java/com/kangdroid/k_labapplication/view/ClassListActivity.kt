package com.kangdroid.k_labapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kangdroid.k_labapplication.databinding.ClassListBinding

class ClassListActivity : AppCompatActivity() {
    private val binding: ClassListBinding by lazy {
        ClassListBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}