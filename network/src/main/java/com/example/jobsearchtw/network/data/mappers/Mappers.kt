package com.example.jobsearchtw.network.data.mappers

import com.example.jobsearchtw.core.domain.model.Offer
import com.example.jobsearchtw.core.domain.model.Vacancy
import com.example.jobsearchtw.network.data.model.dto.OfferDTO
import com.example.jobsearchtw.network.data.model.dto.VacancyDTO

fun OfferDTO.toOffer() = Offer(
    id = id ?: "",
    title = title,
    link = link,
    button = button?.text ?: ""
)

fun VacancyDTO.toVacancy() = Vacancy(
    id = id,
    lookingNumber = lookingNumber,
    title = title,
    town = address.town,
    company = company,
    experience = experience.previewText,
    publishedDate = publishedDate,
    isFavorite = isFavorite,
    salary = salary.short ?: ""
)