package com.example.draganddroprecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.draganddroprecyclerview.databinding.EachItemBinding

class RecyclerAdapter(private val itemList: List<String>) :
    RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: EachItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.textView.text = title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = EachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}