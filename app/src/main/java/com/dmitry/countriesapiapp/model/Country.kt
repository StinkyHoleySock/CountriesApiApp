package com.dmitry.countriesapiapp.model

data class Country(
    val capital: String?,
    val currencies: List<Currency>?,
    val flags: Flags?,
    val name: String?,
    val region: String?,
    val timezones: List<String>?,
)