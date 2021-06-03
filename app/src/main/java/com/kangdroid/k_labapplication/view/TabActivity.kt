package com.kangdroid.k_labapplication.view

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kangdroid.k_labapplication.R
import com.kangdroid.k_labapplication.adapter.TabFragAdapter
import com.kangdroid.k_labapplication.databinding.ActivityTabBinding

class TabActivity : AppCompatActivity() {
    lateinit var binding: ActivityTabBinding
    val textarr = arrayListOf<String>("Class","Ad", "Offline", "MyPage")
    val iconarr = arrayListOf<Int>(R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_phone_android_24, R.drawable.ic_baseline_shopping_cart_24, R.drawable.ic_baseline_person_24)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        binding.viewPager.adapter =
            TabFragAdapter(this)
        //Tab, view 연결
        TabLayoutMediator(binding.tablayout, binding.viewPager){
                tab, position ->
            tab.text = textarr[position]
            tab.setIcon(iconarr[position])
        }.attach()
        val selected = ContextCompat.getColor(this, R.color.black)
        val unselected = ContextCompat.getColor(this, R.color.gray)

        binding.tablayout.getTabAt(0)?.getIcon()?.setColorFilter(selected, PorterDuff.Mode.SRC_IN)
        binding.tablayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val position = tab?.position
                if (position != null) {
                    binding.tablayout.getTabAt(position)?.getIcon()?.setColorFilter(unselected, PorterDuff.Mode.SRC_IN)
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                binding.tablayout.getTabAt(position)?.getIcon()?.setColorFilter(selected, PorterDuff.Mode.SRC_IN)
            }
        })
    }
}