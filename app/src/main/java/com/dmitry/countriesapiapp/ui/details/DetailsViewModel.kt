package com.dmitry.countriesapiapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.countriesapiapp.data.repository.CountryRepository
import com.dmitry.countriesapiapp.model.Country
import com.dmitry.countriesapiapp.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: CountryRepository
) : ViewModel() {

    private val _countryDetails: MutableLiveData<Country> = MutableLiveData()
    val countryDetails: LiveData<Country> get() = _countryDetails
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    fun getCountryDetails(name: String) {
        _loading.value = true
        viewModelScope.launch {
            val response = repository.getCountryDetails(name).body()?.get(0)

            _countryDetails.value = Country(
                name = response?.name,
                capital = response?.capital ?: "No capital",
                currencies = response?.currencies ?: listOf(
                    Currency(
                        "No currency code",
                        "No currency name",
                        "No currency symbol"
                    )
                ),
                flags = response?.flags,
                region = response?.region,
                timezones = response?.timezones
            )
            _loading.postValue(false)
        }
    }
}