package org.sopt.clonegenie.util.MyMusic

import androidx.recyclerview.widget.DiffUtil
import org.sopt.clonegenie.detail.data.ResponseMyMusicPlayListData

class DiffUtilCallback : DiffUtil.ItemCallback<ResponseMyMusicPlayListData.Likes>() {
    override fun areItemsTheSame(
        oldItem: ResponseMyMusicPlayListData.Likes,
        newItem: ResponseMyMusicPlayListData.Likes
    ) =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(
        oldItem: ResponseMyMusicPlayListData.Likes,
        newItem: ResponseMyMusicPlayListData.Likes
    ) =
        (oldItem == newItem)
}