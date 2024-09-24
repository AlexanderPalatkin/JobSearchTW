package com.example.jobsearchtw.core.domain.repository

import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import kotlinx.coroutines.flow.Flow

interface VacancyRepository {
    fun getVacancies() : Flow<Result<List<Vacancy>, ErrorType>>
}