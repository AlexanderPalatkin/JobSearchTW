package com.example.jobsearchtw.search.presentation.search.state

import com.example.jobsearchtw.core.domain.model.Offer

sealed class SearchOffersState {
    data object Start: SearchOffersState()
    data object Loading: SearchOffersState()
    class Data(val offers: List<Offer>): SearchOffersState()
    class Error(val message: String): SearchOffersState()
}