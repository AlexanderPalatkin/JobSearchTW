package com.example.jobsearchtw.network.data.model.dto

import com.google.gson.annotations.SerializedName

data class ApiRequestDTO(
    @SerializedName("offers")
    val offers: List<OfferDTO>,
    @SerializedName("vacancies")
    val vacancies: List<VacancyDTO>
)
