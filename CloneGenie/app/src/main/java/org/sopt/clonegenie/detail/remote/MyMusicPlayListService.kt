package org.sopt.clonegenie.detail.remote

import org.sopt.clonegenie.detail.data.CreatePlayListResponse
import org.sopt.clonegenie.detail.data.ResponseMyMusicPlayListData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyMusicPlayListService {
    @GET("user/1")
    suspend fun getMyMusicPlayList(): Response<ResponseMyMusicPlayListData>

    @FormUrlEncoded
    @POST("playlist")
    suspend fun createPlayList(
        @Field("title") title: String,
        @Field("description") description: String
    ): CreatePlayListResponse
}