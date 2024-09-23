package com.example.jobsearchtw.network.data.model.dto

import com.squareup.moshi.Json

data class OfferDTO(
    @Json(name = "id")
    val id: String?,
    @Json(name = "title")
    val title: String,
    @Json(name = "link")
    val link: String,
    @Json(name = "button")
    val button: ButtonDTO?
)

data class ButtonDTO(
    @Json(name = "text")
    val text: String
)
