package org.sopt.clonegenie.detail.data

data class DetailResponseData(
    val title: String,
    val description: String,
    val total: Int,
    val songs: MutableList<Song>
)

data class Song(
    val name: String,
    val singer: String
)