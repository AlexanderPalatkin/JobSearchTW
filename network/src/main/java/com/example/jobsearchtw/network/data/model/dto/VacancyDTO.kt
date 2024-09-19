package com.example.jobsearchtw.network.data.model.dto

import com.google.gson.annotations.SerializedName

data class VacancyDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("lookingNumber")
    val lookingNumber: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("address")
    val address: AddressDTO,
    @SerializedName("company")
    val company: String,
    @SerializedName("experience")
    val experience: ExperienceDTO,
    @SerializedName("publishedDate")
    val publishedDate: String,
    @SerializedName("isFavorite")
    val isFavorite: Boolean,
    @SerializedName("salary")
    val salary: SalaryDTO,
    @SerializedName("schedules")
    val schedules: List<String>,
    @SerializedName("appliedNumber")
    val appliedNumber: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("responsibilities")
    val responsibilities: String?,
    @SerializedName("questions")
    val questions: List<String>
)

data class AddressDTO(
    @SerializedName("town")
    val town: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("house")
    val house: String
)

data class ExperienceDTO(
    @SerializedName("previewText")
    val previewText: String,
    @SerializedName("text")
    val text: String
)

data class SalaryDTO(
    @SerializedName("short")
    val short: String?,
    @SerializedName("full")
    val full: String
)