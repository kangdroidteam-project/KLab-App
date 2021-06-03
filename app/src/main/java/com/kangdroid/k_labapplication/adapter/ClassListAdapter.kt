package com.kangdroid.k_labapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kangdroid.k_labapplication.data.dto.response.SimplifiedCommunity
import com.kangdroid.k_labapplication.databinding.ClassListRecycleBinding

class ClassListAdapter (val items:ArrayList<SimplifiedCommunity>) : RecyclerView.Adapter<ClassListAdapter.ViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick(view:View, position:Int)
    }

    var itemClickListener: OnItemClickListener? =null

    inner class ViewHolder(val binding: ClassListRecycleBinding) : RecyclerView.ViewHolder(binding.root){
        init{
            binding.apply {
                detail.setOnClickListener {
                    itemClickListener?.OnItemClick(it, adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ClassListRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            contentTitle.text = items[position].contentTitle
            recruitment.text = "${items[position].currentRecruitment} / ${items[position].contentRecruitment}"
            contentNeeds.text = items[position].contentNeeds
        }
    }
}

