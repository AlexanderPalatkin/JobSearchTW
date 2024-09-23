package com.example.jobsearchtw.network.data.api

import com.example.jobsearchtw.network.data.model.dto.ApiRequestDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getData(@Url url: String): Response<ApiRequestDTO>
}