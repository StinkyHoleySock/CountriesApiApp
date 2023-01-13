package com.dmitry.taxiapp.di


import com.dmitry.countriesapiapp.data.repository.CountryRepository
import com.dmitry.countriesapiapp.data.repository.CountryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: CountryRepositoryImpl
    ): CountryRepository
}