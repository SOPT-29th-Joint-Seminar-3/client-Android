package org.sopt.clonegenie.detail.remote

import org.sopt.clonegenie.detail.data.ResponseMyMusicPlayListData
import retrofit2.Response
import retrofit2.http.GET

interface MyMusicPlayListService {
    @GET("user/1")
     suspend fun getMyMusicPlayList():Response<ResponseMyMusicPlayListData>
}