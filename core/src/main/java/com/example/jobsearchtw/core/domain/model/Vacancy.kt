package com.example.jobsearchtw.core.domain.model

data class Vacancy(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val town: String,
    val company: String,
    val experience: String,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: String
)
