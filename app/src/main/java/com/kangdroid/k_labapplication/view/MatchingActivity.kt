package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kangdroid.k_labapplication.adapter.ClassListAdapter
import com.kangdroid.k_labapplication.adapter.MatchingAdapter
import com.kangdroid.k_labapplication.data.dto.request.CommunityAddRequest
import com.kangdroid.k_labapplication.databinding.ClassmatchBinding
import com.kangdroid.k_labapplication.viewmodel.CommunityViewModel

class MatchingActivity : AppCompatActivity(){

    var adapter: MatchingAdapter? = null
    val viewModel: CommunityViewModel by viewModels()

    var classId :Long = 0L
    var title = ""
    var communityTotalRecruitment = 0
    var communityCurrentRecruitment = 0

    private val binding: ClassmatchBinding by lazy {
        ClassmatchBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.liveManagerConfirmCommunity.observe(this, Observer {
            adapter?.update(it.participantsList)
            title = viewModel.liveManagerConfirmCommunity.value!!.communityTitle
            communityTotalRecruitment = viewModel.liveManagerConfirmCommunity.value!!.communityTotalRecruitment
            communityCurrentRecruitment = viewModel.liveManagerConfirmCommunity.value!!.communityCurrentRecruitment
            binding.classtitle.text = title
            binding.contentNeeds.text = "${communityCurrentRecruitment} / ${communityTotalRecruitment}"
        })
        init()
    }

    fun init(){
        if(intent.hasExtra("matching")){
            classId = intent.getLongExtra("matching", -1)
        }

        binding.apply {
            recyelrview.layoutManager = LinearLayoutManager(this@MatchingActivity)

            //데이터 받기
            viewModel.getClassParticipants(classId)
            title = viewModel.liveManagerConfirmCommunity.value!!.communityTitle
            communityTotalRecruitment = viewModel.liveManagerConfirmCommunity.value!!.communityTotalRecruitment
            communityCurrentRecruitment = viewModel.liveManagerConfirmCommunity.value!!.communityCurrentRecruitment

            classtitle.text = title
            contentNeeds.text = "${communityCurrentRecruitment} / ${communityTotalRecruitment}"

            //Adapter
            adapter = MatchingAdapter(mutableListOf())
            adapter?.itemClickListener = object : MatchingAdapter.OnItemClickListener{
                override fun OnItemClick(view: View, position: Int) {
                    //ClassDetailActivity 연결
                    val intent = Intent(this@MatchingActivity, MatchingDetailActivity::class.java)
                    intent.putExtra("userName", adapter!!.getItem(position).userName)
                    intent.putExtra("classId", classId)

                    intent.putExtra("isRequestConfirmed", adapter!!.getItem(position).isRequestConfirmed)
                    intent.putExtra("userIntroduction", adapter!!.getItem(position).userIntroduction)
                    intent.putExtra("title", title)
                    intent.putExtra("communityTotalRecruitment", communityTotalRecruitment)
                    intent.putExtra("communityCurrentRecruitment", communityCurrentRecruitment)

                    startActivityForResult(intent, 200)
                }
            }
            //어뎁터 연결
            recyelrview.adapter = adapter
        }
    }
}