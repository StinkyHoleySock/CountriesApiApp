package com.dmitry.countriesapiapp.data.api

import com.dmitry.countriesapiapp.model.Country
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {
    @GET("/v2/all")
    suspend fun getAllCountries(): Response<ArrayList<Country>>

    @GET("/v2/name/{name}")
    suspend fun getDetails(
        @Path("name") name: String
    ): Response<ArrayList<Country>>

}