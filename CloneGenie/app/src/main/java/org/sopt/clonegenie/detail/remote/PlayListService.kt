package org.sopt.clonegenie.detail.remote

import org.sopt.clonegenie.detail.data.DetailResponseData
import org.sopt.clonegenie.detail.data.DetailStarResponseData
import org.sopt.clonegenie.detail.data.ResponseWrapperData
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlayListService {
    @GET("playlist/{id}")
    suspend fun getDetailPlayList(
        @Path("id")
        playlistId: String
    ): ResponseWrapperData<DetailResponseData>

    @POST("playlist/{id}/like")
    suspend fun getDetailStart(
        @Path("id") username: String
    ): ResponseWrapperData<DetailStarResponseData>

}
