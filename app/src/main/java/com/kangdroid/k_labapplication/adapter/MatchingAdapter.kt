package com.kangdroid.k_labapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kangdroid.k_labapplication.R
import com.kangdroid.k_labapplication.data.SealedUser
import com.kangdroid.k_labapplication.databinding.ClassmatchUserBinding

class MatchingAdapter(var items: List<SealedUser>) : RecyclerView.Adapter<MatchingAdapter.ViewHolder>(){

    interface OnItemClickListener{
        fun OnItemClick(view:View, position:Int)
    }

    fun update(temp: List<SealedUser>){
        items = temp
        notifyDataSetChanged()
    }

    var itemClickListener: OnItemClickListener? =null

    inner class ViewHolder(val binding: ClassmatchUserBinding) : RecyclerView.ViewHolder(binding.root){
        init{
            binding.apply {
                button.setOnClickListener {
                    itemClickListener?.OnItemClick(it, adapterPosition)
                }
            }
        }
    }

    fun getItem(num:Int):SealedUser {
        return items[num]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ClassmatchUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            nickname.text = items[position].userName
            if(items[position].isRequestConfirmed){
                box.setBackgroundResource(R.drawable.box3)
            }
        }
    }
}
