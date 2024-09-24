package com.example.jobsearchtw.search.presentation.search.state

import com.example.jobsearchtw.core.domain.model.Offer
import com.example.jobsearchtw.core.domain.model.Vacancy

sealed class SearchState {
    data class Data(val offers: List<Offer>, val vacancies: List<Vacancy>, val vacanciesCount: Int) : SearchState()
    data class Error(val message: String) : SearchState()
    data object Loading : SearchState()
    data object Start: SearchState()
}