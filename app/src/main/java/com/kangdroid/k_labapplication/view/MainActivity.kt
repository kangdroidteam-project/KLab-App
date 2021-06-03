package com.kangdroid.k_labapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kangdroid.k_labapplication.adapter.MainFragAdapter
import com.kangdroid.k_labapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var viewList = ArrayList<InfoFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    fun init(){
        for(i in 0 until 6){
            viewList.add(InfoFragment(i))
        }
        binding.viewPager.adapter =
            MainFragAdapter(
                this,
                viewList
            )
    }
}