package com.kangdroid.k_labapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kangdroid.k_labapplication.data.SimplifiedMyPageCommunity
import com.kangdroid.k_labapplication.databinding.MyHostClassListRowBinding
import java.text.SimpleDateFormat

class MyHostClassAdapter (val items : List<SimplifiedMyPageCommunity>) : RecyclerView.Adapter<MyHostClassAdapter.ViewHolder>() {

    interface onItemClickListener{
        fun onItemClick(holder : ViewHolder, view : View, dataSimplified : SimplifiedMyPageCommunity, position:Int)
    }

    var itemClickListener : onItemClickListener ?= null

    inner class ViewHolder(val binding : MyHostClassListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(this,it,items[adapterPosition],adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = MyHostClassListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm")
            myClassName.text = items[position].contentTitle
            myClassTime.text = sdf.format(items[position].startTime).toString()
            myClassSupply.text =items[position].contentNeeds

            if(!items[position].isRequestConfirmed){
                isconfirmed.setBackgroundColor(Color.parseColor("#808080"))
            }else{
                isconfirmed.setBackgroundColor(Color.parseColor("#26948D"))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}