package com.example.jobsearchtw.main.presentation.state

sealed class MainFavoritesState {
    data object Start: MainFavoritesState()
    data object Loading : MainFavoritesState()
    class Data(val countFavorites: Int) : MainFavoritesState()
    class Error(val message: String) : MainFavoritesState()
}