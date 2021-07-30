package com.walmart.jikanimage.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.walmart.jikanimage.R
import com.walmart.jikanimage.databinding.ImageItemBinding
import com.walmart.jikanimage.repository.entities.Response

/**
 * Used as [ListAdapter] to show images
 */
class JekinImageListAdapter :
    ListAdapter<Response.ImageData, JekinImageListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.recycle()
    }

    /**
     * [DiffUtil.ItemCallback] for [Response.ImageData]
     */
    class DiffCallback: DiffUtil.ItemCallback<Response.ImageData>() {
        override fun areItemsTheSame(
            oldItem: Response.ImageData,
            newItem: Response.ImageData
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Response.ImageData,
            newItem: Response.ImageData
        ) = oldItem.title == newItem.title && oldItem.url == newItem.url

    }

    inner class ViewHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Update the view
         *
         * @param item Data to show
         */
        fun bind(item: Response.ImageData) {
            binding.apply {
                title.text = item.title

                Glide.with(root.context)
                    .load(item.url)
                    .fitCenter()
                    .skipMemoryCache(true)
                    .into(content)
            }
        }

        /**
         * Clear the memory
         */
        fun recycle() {
            Glide.with(binding.root.context).clear(binding.content)
        }
    }
}