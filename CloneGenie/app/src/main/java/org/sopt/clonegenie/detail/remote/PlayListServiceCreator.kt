package org.sopt.clonegenie.detail.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlayListServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-wesopt29-328c5.cloudfunctions.net/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val myMusicPlayListService = retrofit.create(MyMusicPlayListService::class.java)
    val detailService: PlayListService = retrofit.create(PlayListService::class.java)
}