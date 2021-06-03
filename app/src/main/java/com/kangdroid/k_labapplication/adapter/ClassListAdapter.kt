package com.kangdroid.k_labapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.kangdroid.k_labapplication.data.SimplifiedCommunity
import com.kangdroid.k_labapplication.databinding.ClassListRecycleBinding

class ClassListAdapter(val items: MutableLiveData<List<SimplifiedCommunity>>) : RecyclerView.Adapter<ClassListAdapter.ViewHolder>(){

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

    fun getItem(num:Int):SimplifiedCommunity {
        return items.value!!.get(num)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ClassListRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            contentTitle.text = items.value!!.get(position).contentTitle
            recruitment.text = "${items.value!!.get(position).currentRecruitment} / ${items.value!!.get(position).contentRecruitment}"
            contentNeeds.text = items.value!!.get(position).contentNeeds
        }
    }
}

