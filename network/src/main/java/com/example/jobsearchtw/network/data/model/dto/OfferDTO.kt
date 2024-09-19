package com.example.jobsearchtw.network.data.model.dto

import com.google.gson.annotations.SerializedName

data class OfferDTO(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("button")
    val button: ButtonDTO?
)

data class ButtonDTO(
    @SerializedName("text")
    val text: String
)
