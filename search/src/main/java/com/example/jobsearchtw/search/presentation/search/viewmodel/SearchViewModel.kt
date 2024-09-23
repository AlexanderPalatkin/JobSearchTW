package com.example.jobsearchtw.search.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobsearchtw.core.domain.interactors.OfferVacancyInteractor
import com.example.jobsearchtw.core.domain.model.ErrorType
import com.example.jobsearchtw.core.domain.model.Offer
import com.example.jobsearchtw.core.domain.model.Result
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.search.presentation.search.state.SearchOffersState
import com.example.jobsearchtw.search.presentation.search.state.SearchVacanciesState
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

    private val _offersState = MutableStateFlow<SearchOffersState>(SearchOffersState.Start)
    val offersState = _offersState.asStateFlow()

    private val _vacanciesState = MutableStateFlow<SearchVacanciesState>(SearchVacanciesState.Start)
    val vacanciesState = _vacanciesState.asStateFlow()

    init {
        getOffers()
        getVacancies()
    }

    private fun getOffers() {
        _offersState.update { SearchOffersState.Loading }
        viewModelScope.launch {
            interactor.getOffers().collect { result ->
                processLoadOffers(result)
            }
        }
    }

    private fun getVacancies() {
        _vacanciesState.update { SearchVacanciesState.Loading }
        viewModelScope.launch {
            interactor.getVacancies().collect { result ->
                processLoadVacancies(result)
            }
        }
    }

    private fun processLoadOffers(result: Result<List<Offer>, ErrorType>) {
        when (result) {
            is Result.Success -> {
                _offersState.update { SearchOffersState.Data(result.data) }
            }

            is Result.Error -> {
                _offersState.update { SearchOffersState.Error(result.error.message) }
            }
        }
    }

    private fun processLoadVacancies(result: Result<List<Vacancy>, ErrorType>) {
        when (result) {
            is Result.Success -> {
                _vacanciesState.update { SearchVacanciesState.Data(result.data) }
            }

            is Result.Error -> {
                _vacanciesState.update { SearchVacanciesState.Error(result.error.message) }
            }
        }
    }
}