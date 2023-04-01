package com.example.amphibians.network

import com.example.amphibians.model.AmphibianArticle
import retrofit2.http.GET

interface AmApiService {
    @GET("amphibians")
    suspend fun getArticles(): List<AmphibianArticle>
}