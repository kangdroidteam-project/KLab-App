package com.kangdroid.k_labapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kangdroid.k_labapplication.view.AdFragment
import com.kangdroid.k_labapplication.view.ClassListFragment
import com.kangdroid.k_labapplication.view.MyPageFragment
import com.kangdroid.k_labapplication.view.OfflineFragment

class TabFragAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> ClassListFragment()
            1-> AdFragment()
            2-> OfflineFragment()
            3-> MyPageFragment()
            else -> ClassListFragment()
        }
    }
}