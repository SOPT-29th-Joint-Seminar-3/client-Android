package org.sopt.clonegenie.detail.remote

import org.sopt.clonegenie.detail.data.DetailResponseData
import org.sopt.clonegenie.detail.data.ResponseWrapperData
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailPlayListService {
    @GET("playlist/{id}")
    suspend fun getDetailPlayList(
        @Path("id")
        playlistId: String
    ): ResponseWrapperData<DetailResponseData>
}

