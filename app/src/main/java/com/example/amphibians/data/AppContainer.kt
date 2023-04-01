package com.example.amphibians.data

import com.example.amphibians.network.AmApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val amRepository: AmRepository
}

class DefaultAppContainer: AppContainer {
    private val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: AmApiService by lazy{
        retrofit.create(AmApiService::class.java)
    }

    override val amRepository: AmRepository by lazy{
        NetworkAmRepository(retrofitService)
    }

}