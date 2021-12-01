package org.sopt.clonegenie.detail.data

data class ResponseMyMusicPlayListData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
    ) {
    data class Data(
        val likeCount: Int,
        val saveCount: Int,
        val recentPlayedCount: Int,
        val mostPlayedCount: Int,
        val likes: List<Likes>
    )

    data class Likes(
        val id: Int,
        val title: String,
        val description: String,
    )
}

