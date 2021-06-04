package com.kangdroid.k_labapplication.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.kangdroid.k_labapplication.R
import com.kangdroid.k_labapplication.data.dto.request.CommunityAddRequest
import com.kangdroid.k_labapplication.databinding.ActivityGardenReservationBinding
import com.kangdroid.k_labapplication.repository.ServerRepositoryImpl
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GardenReservationActivity : AppCompatActivity() {
    private val binding : ActivityGardenReservationBinding by lazy {
        ActivityGardenReservationBinding.inflate(layoutInflater)
    }

    var adapter: ArrayAdapter<String> ?= null
    var communityAddRequest:CommunityAddRequest ?= null
    var date:String =""
    var time:String =""
    var timeList = listOf<String>("09-00", "10-00","11-00","12-00","13-00","14-00","15-00","16-00","17-00","18-00","19-00","20-00","21-00")
    var checkSpace = listOf<TextView>(binding.A, binding.B, binding.C, binding.D)
    var checkTime = listOf<TextView>(binding.time1, binding.time2, binding.time3, binding.time4,binding.time5,
        binding.time6, binding.time7, binding.time8, binding.time9,binding.time10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initData()
        init()
    }

    private fun initData() {
        adapter = ArrayAdapter(this@GardenReservationActivity, R.layout.support_simple_spinner_dropdown_item, ArrayList<String>())

        val calendar = Calendar.getInstance()
        for(i in 1 until 8){
            calendar.add(Calendar.DAY_OF_MONTH, i)
            val oneDate = calendar.time
            adapter!!.add(SimpleDateFormat("yyyy-MM-dd").format(oneDate))
        }
    }


    private fun init(){
        if(intent.hasExtra("CommunityAddRequest")){
            communityAddRequest = intent.getSerializableExtra("CommunityAddRequest") as CommunityAddRequest
        }
        if(communityAddRequest==null)return


        binding.apply {
            //날짜 스피너
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    date = adapter?.getItem(0).toString()
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    date = adapter?.getItem(position).toString()
                }
            }

            //공간
            for(v in checkSpace){
                v.setOnClickListener {
                    if(v.background == R.drawable.border_200 as Drawable){
                        for(temp in checkSpace){
                            temp.setBackgroundResource(R.drawable.border_200)
                        }
                        v.setBackgroundResource(R.drawable.green_box1)
                        communityAddRequest!!.gardenReservationRequest.reservationSpace = v.text.toString()
                    }
                }
            }

            //시간
            var num = 0
            for (v in checkTime){
                v.setOnClickListener {
                    if(v.background == R.drawable.border_200 as Drawable){
                        for(temp in checkSpace){
                            temp.setBackgroundResource(R.drawable.border_200)
                        }
                        v.setBackgroundResource(R.drawable.fill_border__700)
                        time = timeList[num]
                    }
                }
                num++
            }

            //타임스템프 변환
            var str = "${date}-${time}"
            val sdf = SimpleDateFormat("yyyy-MM-dd-hh-mm")
            val date= sdf.parse(str)
            val timestamp = date.getTime()

            communityAddRequest!!.gardenReservationRequest.reservationStartTime = timestamp

            //create
            create.setOnClickListener {
                ServerRepositoryImpl.addClass(
                    communityAddRequest = communityAddRequest!!
                )
            }
        }
    }
}
