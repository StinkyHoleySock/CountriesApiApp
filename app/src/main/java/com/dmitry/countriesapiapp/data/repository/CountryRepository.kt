package com.dmitry.countriesapiapp.data.repository

import com.dmitry.countriesapiapp.model.Country
import retrofit2.Response

interface CountryRepository {
    suspend fun getOrdersList(): Response<ArrayList<Country>>
}