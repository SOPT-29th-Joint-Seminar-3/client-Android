package org.sopt.clonegenie.detail.data

data class ResponseWrapperData<T>(
    val status: Int,
    val message: String,
    val data: T? = null
)