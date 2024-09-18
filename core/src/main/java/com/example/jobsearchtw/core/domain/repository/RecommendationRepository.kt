package com.example.jobsearchtw.core.domain.repository

import com.example.jobsearchtw.core.domain.model.Recommendation
import kotlinx.coroutines.flow.Flow

interface RecommendationRepository {
    fun getRecommendations() : Flow<List<Recommendation>>
}