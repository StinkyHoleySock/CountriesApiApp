package com.dmitry.countriesapiapp.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class Country(
    @SerializedName("capital") val capital: String,
    @SerializedName("currencies") val currencies: List<Currency>,
    @SerializedName("flags") val flags: Flags,
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("timezones") val timezones: List<String>,
) : Serializable