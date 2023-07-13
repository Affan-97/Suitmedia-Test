package com.affan.suitmedia.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.affan.suitmedia.databinding.ItemUserBinding
import com.affan.suitmedia.network.DataItem
import com.bumptech.glide.Glide

class ListAdapter(private val clickListener: (DataItem) -> Unit) : PagingDataAdapter<DataItem, ListAdapter.ViewHolder>(DIFF_CALLBACK) {


    class ViewHolder(private val binding: ItemUserBinding,  private val clickListener: (DataItem) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: DataItem) {
            binding.tvName.text = "${data.firstName} ${data.lastName}"
            binding.tvEmail.text = data.email
            Glide.with(itemView).load(data.avatar).into(binding.ivUser)
            itemView.setOnClickListener {
               clickListener(data)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,clickListener)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}