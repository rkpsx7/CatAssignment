package com.dev_akash.catassignment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev_akash.catassignment.databinding.LoadingFooterBinding

class PagingLoadingAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PagingLoadingAdapter.PagingLoadingVH>() {

    inner class PagingLoadingVH(private val binding: LoadingFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(state: LoadState) {
            binding.progressBar.isVisible = state is LoadState.Loading
            binding.retryButton.apply {
                isVisible = state is LoadState.Error
                setOnClickListener {
                    retry.invoke()
                }
            }


        }

    }

    override fun onBindViewHolder(holder: PagingLoadingVH, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PagingLoadingVH {
        return PagingLoadingVH(
            LoadingFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}