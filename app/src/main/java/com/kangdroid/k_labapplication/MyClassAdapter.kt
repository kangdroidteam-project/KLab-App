package com.kangdroid.k_labapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kangdroid.k_labapplication.databinding.MyClassListRowBinding

class MyClassAdapter(var items: ArrayList<Class>): RecyclerView.Adapter<MyClassAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: MyClassListRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    fun addItem(item: Class) = items.add(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MyClassListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            myClassName.text = items[position].className
            myClassTime.text = items[position].classTime
            myClassSupply.text = items[position].supplies
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}