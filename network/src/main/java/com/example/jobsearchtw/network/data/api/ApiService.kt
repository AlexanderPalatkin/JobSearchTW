package com.example.jobsearchtw.network.data.api

import com.example.jobsearchtw.network.data.model.dto.ApiRequestDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(NetworkParams.GET_PATH)
    fun getData(
        @Query("id") id: String = NetworkParams.ID_PATH,
        @Query("export") export: String = NetworkParams.EXPORT_PATH
    ): Response<ApiRequestDTO>
}