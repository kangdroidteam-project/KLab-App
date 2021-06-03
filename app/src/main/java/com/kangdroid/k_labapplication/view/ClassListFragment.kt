package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kangdroid.k_labapplication.adapter.ClassListAdapter
import com.kangdroid.k_labapplication.databinding.ClassListBinding
import com.kangdroid.k_labapplication.viewmodel.CommunityViewModel

class ClassListFragment : Fragment() {
    var binding: ClassListBinding?=null

    var adapter: ClassListAdapter? = null
    val viewModel: CommunityViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveSimpleClassList.observe(viewLifecycleOwner, Observer {
            Log.d("ClassList", "아직 안됨")
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ClassListBinding.inflate(layoutInflater, container, false)

        //리사이클러뷰 설정
        binding?.apply {
            recyclerView.layoutManager = LinearLayoutManager(activity)

            //API로 데이터 받기
            viewModel.getClassList()

            //Adapter
            adapter = ClassListAdapter(viewModel.liveSimpleClassList)
            adapter?.itemClickListener = object : ClassListAdapter.OnItemClickListener{
                override fun OnItemClick(view: View, position: Int) {
                    //ClassDetailActivity 연결
                    val intent = Intent(context, ClassDetailActivity::class.java)
                    intent.putExtra("id", adapter!!.getItem(position).id)
                    startActivity(intent)
                }
            }
        }
        return binding!!.root
    }


}