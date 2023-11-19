package com.example.data.remote

import com.example.data.remote.dto.UniversityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityApi {

    @GET("search")
    suspend fun getUniversitiesByNameAndCountry(
        @Query("name") name: String,
        @Query("country") country: String,
        @Query("limit") limit:Int,
        @Query("offset") offset:Int
    ): UniversityResponse

}