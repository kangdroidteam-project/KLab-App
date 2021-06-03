package com.kangdroid.k_labapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kangdroid.k_labapplication.data.dto.response.SimplifiedCommunity
import com.kangdroid.k_labapplication.databinding.ClassListBinding

class ClassListFragment : Fragment() {
    var binding: ClassListBinding?=null

    var adapter: ClassListAdapter? = null
    var list :ArrayList<SimplifiedCommunity> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ClassListBinding.inflate(layoutInflater, container, false)

        //리사이클러뷰 설정
        binding?.apply {
            recyclerView.layoutManager = LinearLayoutManager(activity)

            //API로 데이터 받기

            //Adapter
            adapter = ClassListAdapter(list)
            adapter?.itemClickListener = object : ClassListAdapter.OnItemClickListener{
                override fun OnItemClick(view: View, position: Int) {
                    //ClassDetailActivity 연결
                }
            }
        }
        return binding!!.root
    }
}