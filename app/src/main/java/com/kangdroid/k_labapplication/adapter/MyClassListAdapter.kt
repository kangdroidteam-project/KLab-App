package com.kangdroid.k_labapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kangdroid.k_labapplication.databinding.MyClassListRowBinding
import com.kangdroid.k_labapplication.view.SimplifiedMyPageCommunity

class MyClassListAdapter(val items : ArrayList<SimplifiedMyPageCommunity>) : RecyclerView.Adapter<MyClassListAdapter.ViewHolder>() {

    interface onItemClickListener{
        fun onItemClick(holder : ViewHolder, view : View, dataSimplified : SimplifiedMyPageCommunity, position:Int)
    }

    var itemClickListener : onItemClickListener ?= null

    inner class ViewHolder(val binding : MyClassListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(this,it,items[adapterPosition],adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = MyClassListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            myClassName.text = items[position].contentTitle
            myClassTime.text = items[position].startTime.toString()
            myClassSupply.text = items[position].contentNeeds

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