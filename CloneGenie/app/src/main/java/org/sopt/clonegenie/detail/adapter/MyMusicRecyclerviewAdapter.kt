package org.sopt.clonegenie.detail.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.clonegenie.R
import org.sopt.clonegenie.databinding.ItemMyMusicBinding
import org.sopt.clonegenie.detail.data.ResponseMyMusicPlayListData

class MyMusicRecyclerviewAdapter(val musicList: List<ResponseMyMusicPlayListData.Likes>,private val clickListener: (Int) -> Unit) :
    ListAdapter<ResponseMyMusicPlayListData.Likes, MyMusicRecyclerviewAdapter.ViewHolder>(org.sopt.clonegenie.util.MyMusic.DiffUtilCallback()) {
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
        val resource = holder.itemView.resources
        holder.onBind(getItem(position),clickListener,position,resource)
    }

    class ViewHolder(private val binding: ItemMyMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseMyMusicPlayListData.Likes, clickListener: (Int) -> Unit,position: Int,resources : Resources) {
            val noteDesignArray = resources.obtainTypedArray(R.array.cover)
            val noteResourceId = noteDesignArray.getResourceId(position % 10, 0)

            binding.tvPlaylist.text = data.title
            binding.tvCount.text = data.description
            Glide.with(itemView.context).load(noteResourceId).into(binding.ivCover)
            binding.root.setOnClickListener {
                clickListener(position)
            }
        }
    }

    companion object {
        const val DEFAULT_COUNT = 5
    }
}