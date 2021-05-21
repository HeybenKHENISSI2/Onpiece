package com.example.moto.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OnepieceApi {
    @GET("chapters/all")
    fun getOnepieceList(): Call<OnepieceListResponse>

    @GET("chapters/{id}")
    fun getOnepieceDetail(@Path("id") id: Int): Call<OnepieceDetailResponse>

}