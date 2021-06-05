package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.kangdroid.k_labapplication.data.Community
import com.kangdroid.k_labapplication.databinding.ClassDetailBinding
import com.kangdroid.k_labapplication.repository.ServerRepositoryImpl.getDetailedClass
import com.kangdroid.k_labapplication.viewmodel.CommunityViewModel
import java.util.*
import javax.xml.bind.DatatypeConverter


class ClassDetailActivity : AppCompatActivity() {
    val viewModel: CommunityViewModel by viewModels()
    lateinit var data:Community

    private val binding : ClassDetailBinding by lazy {
        ClassDetailBinding.inflate(layoutInflater)
    }

    var id : Long = 0L



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(intent.hasExtra("id")){
            id = intent.getLongExtra("id",-1)
            Log.e("여기 맞죠?!!?!?!?",id.toString())
        }

        if(intent.hasExtra("jiwoo")){
            Log.e("지우지우?!!?!?!?", intent.getStringExtra("jiwoo").toString())
        }

        init()
        viewModel.getDetailedClass(id)
    }

    private fun init(){
        viewModel.liveCommunity.observe(this@ClassDetailActivity, androidx.lifecycle.Observer {
//            if(id==-1L)return
            data = it

            Log.e("KDR", "NOF: ${data.gardenReservation.reservationSpace}")

            binding.apply {
                title.text = data.contentTitle
                val autext = "Author : ${data.contentAuthor}"
                nickname.text = autext
                if(data.contentPicture!=null){
                    //string to bitmap
                    Log.e(this::class.java.simpleName, data.contentPicture ?: "")
                    val decodedArray: ByteArray = DatatypeConverter.parseBase64Binary(data.contentPicture)
                    val bitmapTmp: Bitmap = BitmapFactory.decodeByteArray(decodedArray, 0, decodedArray.size)
                    image.setImageBitmap(bitmapTmp)
                }
                content.text = data.innerContent
                needs.text = data.contentNeeds
                deadline.text = data.contentDeadline
                firstmeeting.text = data.firstMeeting
                firstmeetingplace.text = data.gardenReservation.reservationSpace
                recruitment.text = data.contentRecruitment.toString()

                joinbtn.setOnClickListener {
                    val intent = Intent(this@ClassDetailActivity, MyClassListActivity::class.java)
                    intent.putExtra("id",id)
                    startActivity(intent)
                }
            }
        })

    }

}