package com.kangdroid.k_labapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kangdroid.k_labapplication.databinding.OnlineMarketRowBinding

class OnlineProductAdapter (var items: ArrayList<OnlineProduct>): RecyclerView.Adapter<OnlineProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: OnlineMarketRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    fun addItem(item: OnlineProduct) = items.add(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = OnlineMarketRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            onlineImg.setImageResource(items[position].img)
            onlineImg.clipToOutline = true // 모서리 둥글게
            onlineTitle.text = items[position].title
            onlineNickname.text = items[position].nickName
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}