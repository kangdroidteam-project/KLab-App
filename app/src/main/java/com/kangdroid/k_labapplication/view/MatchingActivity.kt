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
        init()
        viewModel.getClassParticipants(classId)
    }

    fun init(){
        if(intent.hasExtra("matching")){
            classId = intent.getLongExtra("matching", -1)
        }

        viewModel.liveManagerConfirmCommunity.observe(this@MatchingActivity, Observer {
            binding.apply {

                recyelrview.layoutManager = LinearLayoutManager(this@MatchingActivity)

                Log.e("MATCHINGACTIVITY","CLASSID : $classId")

                title = it.communityTitle
                communityTotalRecruitment = it.communityTotalRecruitment
                communityCurrentRecruitment = it.communityCurrentRecruitment

                classtitle.text = title
                contentNeeds.text = "${communityCurrentRecruitment} / ${communityTotalRecruitment}"

                //Adapter
                adapter = MatchingAdapter(it.participantsList)
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
        })
    }

    override fun onResume() {
        super.onResume()
        Log.e("ONRESUME","여끼여끼여끼여끼엮여끼")
        viewModel.getClassParticipants(classId)
    }
}