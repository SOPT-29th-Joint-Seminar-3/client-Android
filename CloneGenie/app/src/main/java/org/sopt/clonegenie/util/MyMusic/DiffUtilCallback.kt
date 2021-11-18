package org.sopt.clonegenie.util.MyMusic

import androidx.recyclerview.widget.DiffUtil
import org.sopt.clonegenie.detail.data.MyMusicData

class DiffUtilCallback : DiffUtil.ItemCallback<MyMusicData>() {
    override fun areItemsTheSame(
        oldItem: MyMusicData,
        newItem: MyMusicData
    ) =
        (oldItem.playList == newItem.playList)

    override fun areContentsTheSame(
        oldItem: MyMusicData,
        newItem: MyMusicData
    ) =
        (oldItem == newItem)
}