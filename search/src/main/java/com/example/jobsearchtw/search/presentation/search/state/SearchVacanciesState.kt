package com.example.jobsearchtw.search.presentation.search.state

import com.example.jobsearchtw.core.domain.model.Vacancy

sealed class SearchVacanciesState {
    data object Start: SearchVacanciesState()
    data object Loading : SearchVacanciesState()
    class Data(val vacancies: List<Vacancy>) : SearchVacanciesState()
    class Error(val message: String) : SearchVacanciesState()
}