package com.example.moto.presentation

import com.example.moto.presentation.OnepieceApplication.Companion.context
import com.example.moto.presentation.api.OnepieceApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singletons {
    companion object {
        var cache = Cache(File(context?.cacheDir, "responses"), 10 * 1024 * 1024) // 10 MiB
        val okhttpClient: OkHttpClient = OkHttpClient().newBuilder()
                .cache(cache)
                .build()
        val onepieceApi: OnepieceApi = Retrofit.Builder()
            .baseUrl("https://onepiececover.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient)
            .build()
            .create(OnepieceApi::class.java)

    }
}