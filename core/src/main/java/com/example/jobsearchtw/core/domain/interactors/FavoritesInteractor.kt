package com.example.jobsearchtw.core.domain.interactors

import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.core.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesInteractor @Inject constructor(
    private val repository: FavoritesRepository
) {
    fun getFavorites(): Flow<Result<List<Vacancy>, ErrorType>> = repository.getFavorites()
}