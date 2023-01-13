package com.dmitry.countriesapiapp.di

import com.dmitry.countriesapiapp.Constants
import com.dmitry.countriesapiapp.data.api.CountryApi
import com.dmitry.countriesapiapp.data.repository.CountryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): CountryApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(service: CountryApi): CountryRepositoryImpl = CountryRepositoryImpl(service)

}

