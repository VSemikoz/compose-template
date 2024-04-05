package com.example.compose_template.data.network

import com.example.compose_template.data.models.person.PersonResultData
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApi {
    @GET("api/")
    suspend fun getPersonList(@Query("results") count: Int): PersonResultData
}