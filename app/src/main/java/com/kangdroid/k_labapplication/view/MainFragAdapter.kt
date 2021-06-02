package com.kangdroid.k_labapplication.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainFragAdapter (fragmentActivity: FragmentActivity, var viewList: ArrayList<InfoFragment>) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        return viewList[position]
    }
}