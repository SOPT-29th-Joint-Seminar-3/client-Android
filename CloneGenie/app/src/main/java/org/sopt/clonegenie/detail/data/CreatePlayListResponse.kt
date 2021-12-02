package org.sopt.clonegenie.detail.data

data class CreatePlayListResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
)


data class Data(
    val id: Int,
    val title: String,
    val description: String,
)