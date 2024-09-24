package com.example.jobsearchtw.network.data.model.dto

import com.squareup.moshi.Json

data class VacancyDTO(
    @Json(name = "id")
    val id: String,
    @Json(name = "lookingNumber")
    val lookingNumber: Int = 0,
    @Json(name = "title")
    val title: String,
    @Json(name = "address")
    val address: AddressDTO,
    @Json(name = "company")
    val company: String,
    @Json(name = "experience")
    val experience: ExperienceDTO,
    @Json(name = "publishedDate")
    val publishedDate: String,
    @Json(name = "isFavorite")
    val isFavorite: Boolean,
    @Json(name = "salary")
    val salary: SalaryDTO,
    @Json(name = "schedules")
    val schedules: List<String>,
    @Json(name = "appliedNumber")
    val appliedNumber: Int?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "responsibilities")
    val responsibilities: String?,
    @Json(name = "questions")
    val questions: List<String>
)

data class AddressDTO(
    @Json(name = "town")
    val town: String,
    @Json(name = "street")
    val street: String,
    @Json(name = "house")
    val house: String
)

data class ExperienceDTO(
    @Json(name = "previewText")
    val previewText: String,
    @Json(name = "text")
    val text: String
)

data class SalaryDTO(
    @Json(name = "short")
    val short: String?,
    @Json(name = "full")
    val full: String
)