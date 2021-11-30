package org.sopt.clonegenie.util

import androidx.recyclerview.widget.DiffUtil
import org.sopt.clonegenie.detail.data.Song

class DiffUtilCallback : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(
        oldItem: Song,
        newItem: Song
    ) =
        (oldItem.name == newItem.name)

    override fun areContentsTheSame(
        oldItem: Song,
        newItem: Song
    ) =
        (oldItem == newItem)
}