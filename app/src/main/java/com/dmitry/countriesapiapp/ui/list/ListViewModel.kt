package com.dmitry.countriesapiapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.countriesapiapp.data.repository.CountryRepositoryImpl
import com.dmitry.countriesapiapp.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: CountryRepositoryImpl,
) : ViewModel() {

    private val _listOfCountries: MutableLiveData<ArrayList<Country>> = MutableLiveData()
    val listOfCountries: LiveData<ArrayList<Country>> get() = _listOfCountries
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading

    fun getCountries() {
        _loading.value = true
        viewModelScope.launch {
            _listOfCountries.value = (repository.getOrdersList().body())
            _loading.postValue(false)
        }
    }

}