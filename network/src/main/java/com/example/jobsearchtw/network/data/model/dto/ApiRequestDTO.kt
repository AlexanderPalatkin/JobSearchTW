package com.example.jobsearchtw.network.data.model.dto

import com.squareup.moshi.Json

data class ApiRequestDTO(
    @Json(name = "offers")
    val offers: List<OfferDTO>,
    @Json(name = "vacancies")
    val vacancies: List<VacancyDTO>
)
