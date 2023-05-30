package com.dev_akash.catassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev_akash.catassignment.databinding.CatItemLayoutBinding
import com.dev_akash.catassignment.data.model.CatsDto
import com.dev_akash.catassignment.utils.loadImage

class CatsPagingAdapter : PagingDataAdapter<CatsDto, CatsPagingAdapter.CatsPagingVH>(COMPARATOR) {

    inner class CatsPagingVH(private val binding: CatItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(catsDto: CatsDto) {
            binding.apply {
                ivImage.loadImage(catsDto.url)
                catsDto.breeds.firstOrNull()?.let {
                    tvName.text = it.name
                    tvDesc.text = it.description
                }
            }
        }
    }

    override fun onBindViewHolder(holder: CatsPagingVH, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsPagingVH {
        return CatsPagingVH(
            CatItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CatsDto>() {
            override fun areItemsTheSame(oldItem: CatsDto, newItem: CatsDto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CatsDto, newItem: CatsDto): Boolean {
                return oldItem == newItem
            }

        }
    }
}