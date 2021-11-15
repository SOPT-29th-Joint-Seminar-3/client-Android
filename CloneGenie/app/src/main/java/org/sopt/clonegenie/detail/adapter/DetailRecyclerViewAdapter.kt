package org.sopt.clonegenie.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.clonegenie.databinding.ItemDetailBinding
import org.sopt.clonegenie.detail.data.DetailData
import org.sopt.clonegenie.util.DiffUtilCallback

class DetailRecyclerViewAdapter(val musicList: MutableList<DetailData>) : ListAdapter<DetailData, DetailRecyclerViewAdapter.ViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = musicList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: DetailData) {
            binding.tvSong.text = data.song
            binding.tvSinger.text = data.singer
            Glide.with(itemView.context).load(data.cover).into(binding.ivCover)
        }
    }
}