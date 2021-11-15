package org.sopt.clonegenie.util

import androidx.recyclerview.widget.DiffUtil
import org.sopt.clonegenie.detail.data.DetailData

class DiffUtilCallback : DiffUtil.ItemCallback<DetailData>() {
    override fun areItemsTheSame(
        oldItem: DetailData,
        newItem: DetailData
    ) =
        (oldItem.song == newItem.song)

    override fun areContentsTheSame(
        oldItem: DetailData,
        newItem: DetailData
    ) =
        (oldItem == newItem)
}