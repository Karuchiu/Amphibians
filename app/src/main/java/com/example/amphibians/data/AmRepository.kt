package com.example.amphibians.data

import com.example.amphibians.model.AmphibianArticle
import com.example.amphibians.network.AmApiService

interface AmRepository {
    suspend fun getAmArticles(): List<AmphibianArticle>
}

class NetworkAmRepository(
    private val amApiService: AmApiService
): AmRepository{
    override suspend fun getAmArticles(): List<AmphibianArticle> {
        return amApiService.getArticles()
    }

}