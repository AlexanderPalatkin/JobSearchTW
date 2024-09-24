package com.example.jobsearchtw.search.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearchtw.core.domain.interactors.OfferVacancyInteractor
import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Offer
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.search.presentation.search.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val interactor: OfferVacancyInteractor
) : ViewModel() {
    private val _state = MutableStateFlow<SearchState>(SearchState.Start)
    val state = _state.asStateFlow()

    private var offers: List<Offer> = emptyList()
    private var vacancies: List<Vacancy> = emptyList()

    init {
        getData()
    }

    private fun getData() {
        _state.update { SearchState.Loading }

        viewModelScope.launch {
            interactor.getOffers().collect { result ->
                processLoadOffers(result)
            }

            interactor.getVacancies().collect { result ->
                processLoadVacancies(result)
            }

            if (offers.isNotEmpty() && vacancies.isNotEmpty()) {
                _state.update { SearchState.Data(offers, vacancies, vacancies.size) }
            }
        }
    }

    private fun processLoadOffers(result: Result<List<Offer>, ErrorType>) {
        when (result) {
            is Result.Success -> {
                offers = result.data
                if (vacancies.isNotEmpty()) {
                    _state.update { SearchState.Data(offers, vacancies, vacancies.size) }
                }
            }
            is Result.Error -> {
                _state.update { SearchState.Error(result.error.message) }
            }
        }
    }

    private fun processLoadVacancies(result: Result<List<Vacancy>, ErrorType>) {
        when (result) {
            is Result.Success -> {
                vacancies = result.data
                if (offers.isNotEmpty()) {
                    _state.update { SearchState.Data(offers, vacancies, vacancies.size) }
                }
            }
            is Result.Error -> {
                _state.update { SearchState.Error(result.error.message) }
            }
        }
    }
}