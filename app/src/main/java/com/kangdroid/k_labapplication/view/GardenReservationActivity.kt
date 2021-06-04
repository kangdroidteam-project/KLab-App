package com.kangdroid.k_labapplication.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.databind.node.BooleanNode
import com.kangdroid.k_labapplication.R
import com.kangdroid.k_labapplication.data.dto.request.CommunityAddRequest
import com.kangdroid.k_labapplication.databinding.ActivityGardenReservationBinding
import com.kangdroid.k_labapplication.repository.ServerRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    var spaceFlag = ArrayList<Boolean>()
    var timeFlag = ArrayList<Boolean>()
    var temp = ""

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
        spaceFlag.add(false)
        spaceFlag.add(false)
        spaceFlag.add(false)
        spaceFlag.add(false)

        for(i in 0 until 12){
            timeFlag.add(false)
        }
    }


    private fun init(){
        if(intent.hasExtra("CommunityAddRequest")){
            communityAddRequest = intent.getSerializableExtra("CommunityAddRequest") as CommunityAddRequest
        }
        if(communityAddRequest==null)return

        var checkSpace = listOf<TextView>(binding.A, binding.B, binding.C, binding.D)
        var checkTime = listOf<TextView>(binding.time1, binding.time2, binding.time3, binding.time4,binding.time5,
            binding.time6, binding.time7, binding.time8, binding.time9,binding.time10)

        Log.e("temp String",communityAddRequest!!.contentAuthor.toString())

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
                    var num = -1

                    when(v.text.toString()){
                        "A" -> num = 0
                        "B" -> num = 1
                        "C" -> num = 2
                        "D" -> num = 3
                    }
                    if(!spaceFlag[num]){ //true 버튼 눌림
                        for(temp in checkSpace){
                            temp.setBackgroundResource(R.drawable.border_200)
                        }
                        v.setBackgroundResource(R.drawable.green_box1)
                        communityAddRequest!!.gardenReservationRequest.reservationSpace = v.text.toString()
                        for(i in 0 until 4){
                            spaceFlag[i] = false
                        }
                        spaceFlag[num] = true
                    }
                }
            }

            //시간
            for (v in checkTime){
                v.setOnClickListener {

                    var num = 0
                    when(v.text.toString()){
                        "09:00 ~ 10:00" -> num = 0
                        "10:00 ~ 11:00" -> num = 1
                        "11:00 ~ 12:00" -> num = 2
                        "12:00 ~ 13:00" -> num = 3

                        "13:00 ~ 14:00" -> num = 4
                        "14:00 ~ 15:00" -> num = 5
                        "15:00 ~ 16:00" -> num = 6
                        "16:00 ~ 17:00" -> num = 7

                        "17:00 ~ 18:00" -> num = 8
                        "18:00 ~ 19:00" -> num = 9
                        "19:00 ~ 20:00" -> num = 10
                        "21:00 ~ 22:00" -> num = 11
                    }
                    if(!timeFlag[num]){
                        for(temp in checkTime){
                            temp.setBackgroundResource(R.drawable.border_200)
                        }
                        v.setBackgroundResource(R.drawable.fill_border__700)
                        time = timeList[num]
                        for(i in 0 until 12){
                            timeFlag[i] = false
                        }
                        timeFlag[num] = true
                    }
                }
            }


            val sdf = SimpleDateFormat("yyyy-MM-dd")
//            val timestamp = sdf.parse(date).time

//            communityAddRequest!!.gardenReservationRequest.reservationStartTime = timestamp

            communityAddRequest!!.gardenReservationRequest.reservationStartTime = 1564981


            val scope = CoroutineScope(Dispatchers.IO)

            //create
            create.setOnClickListener {
                scope.launch {
                        runCatching {
                            ServerRepositoryImpl.addClass(
                                communityAddRequest = communityAddRequest!!
                            )
                            Log.e("addClass Temp", "true")

                        }.onSuccess {
                            withContext(Dispatchers.Main){
                                val intent = Intent(this@GardenReservationActivity,TabActivity::class.java)
                                startActivity(intent)
                            }
                        }
                }
            }
        }
    }
}
