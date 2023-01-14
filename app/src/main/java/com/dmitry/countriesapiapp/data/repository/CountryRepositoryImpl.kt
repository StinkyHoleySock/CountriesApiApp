package com.dmitry.countriesapiapp.data.repository

import com.dmitry.countriesapiapp.data.api.CountryApi
import com.dmitry.countriesapiapp.model.Country
import retrofit2.Response
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val service: CountryApi
) : CountryRepository {

    override suspend fun getOrdersList(): Response<ArrayList<Country>> {
        return service.getAllCountries()
    }

    override suspend fun getCountryDetails(name: String): Response<ArrayList<Country>> {
        return service.getDetails(name)
    }
}