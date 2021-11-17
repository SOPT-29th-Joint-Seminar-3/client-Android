package org.sopt.clonegenie.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.clonegenie.databinding.ItemMyMusicBinding
import org.sopt.clonegenie.detail.data.MyMusicData

class MyMusicRecyclerviewAdapter(val musicList: MutableList<MyMusicData>,private val clickListener: (Int) -> Unit) :
    ListAdapter<MyMusicData, MyMusicRecyclerviewAdapter.ViewHolder>(org.sopt.clonegenie.util.MyMusic.DiffUtilCallback()) {
    var loadStatus = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMyMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() =
        if (loadStatus) {
            if (DEFAULT_COUNT > musicList.size) {
                musicList.size
            } else {
                DEFAULT_COUNT
            }
        } else {
            musicList.size
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position),clickListener,position)
    }

    class ViewHolder(private val binding: ItemMyMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MyMusicData, clickListener: (Int) -> Unit,position: Int) {
            binding.tvPlaylist.text = data.playList
            binding.tvCount.text = data.count.toString() + "곡"
            Glide.with(itemView.context).load(data.cover).into(binding.ivCover)
            binding.root.setOnClickListener {
                clickListener(position)
            }
        }
    }

    companion object {
        const val DEFAULT_COUNT = 5
    }
}