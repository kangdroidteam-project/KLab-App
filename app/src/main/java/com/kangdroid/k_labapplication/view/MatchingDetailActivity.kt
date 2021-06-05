package com.kangdroid.k_labapplication.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kangdroid.k_labapplication.R
import com.kangdroid.k_labapplication.adapter.MatchingAdapter
import com.kangdroid.k_labapplication.databinding.ClassmatchBinding
import com.kangdroid.k_labapplication.databinding.ProfileManagerBinding
import com.kangdroid.k_labapplication.repository.ServerRepositoryImpl
import com.kangdroid.k_labapplication.viewmodel.CommunityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchingDetailActivity : AppCompatActivity(){
    private val binding: ProfileManagerBinding by lazy {
        ProfileManagerBinding.inflate(layoutInflater)
    }

    var userName = ""
    var classId :Long = 0L
    var title = ""
    var introduction = ""
    var communityTotalRecruitment = 0
    var communityCurrentRecruitment = 0
    var isRequestConfirmed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        if(intent.hasExtra("userName")&& intent.hasExtra("classId")&&intent.hasExtra("userIntroduction")
            && intent.hasExtra("title")&& intent.hasExtra("communityTotalRecruitment") && intent.hasExtra("communityCurrentRecruitment")&& intent.hasExtra("isRequestConfirmed")){

            userName = intent.getStringExtra("userName").toString()
            classId = intent.getLongExtra("classId", -1)
            title = intent.getStringExtra("title").toString()
            introduction = intent.getStringExtra("userIntroduction").toString()
            communityTotalRecruitment = intent.getIntExtra("communityTotalRecruitment",-1)
            communityCurrentRecruitment = intent.getIntExtra("communityCurrentRecruitment",-1)
            isRequestConfirmed = intent.getBooleanExtra("isRequestConfirmed",false)

        }

        binding.apply {
            classtitle.text = title
            contentNeeds.text = "${communityCurrentRecruitment} / ${communityTotalRecruitment}"
            name.text = "User name: ${userName}"
            userIntroduction.text = introduction.toString()
            if(isRequestConfirmed){
                invite.setBackgroundResource(R.drawable.box5)
            }

            val scope = CoroutineScope(Dispatchers.IO)
            invite.setOnClickListener {
                if(communityCurrentRecruitment>=communityTotalRecruitment){
                    Toast.makeText(this@MatchingDetailActivity, "You can't invite any more",Toast.LENGTH_SHORT).show()
                }else if(isRequestConfirmed){
                    Toast.makeText(this@MatchingDetailActivity, "Already invited.",Toast.LENGTH_SHORT).show()
                }else{
                    scope.launch {
                        runCatching {
                            ServerRepositoryImpl.confirmClassParticipants(classId, userName)
                        }.onSuccess {
                            withContext(Dispatchers.Main){
                                val intent = Intent()
                                setResult(Activity.RESULT_OK, intent)
                                finish()
                            }
                        }
                    }
                }
            }
        }
    }
}