package com.dmitry.countriesapiapp.data.api

import com.dmitry.countriesapiapp.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {
    @GET(" ")
    suspend fun getAllCountries() : Response<ArrayList<Country>>
}