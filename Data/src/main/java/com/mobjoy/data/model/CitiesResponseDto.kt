package com.mobjoy.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CitiesResponseDto(
    val citiesResponseDto: List<CitiesResponseDtoItem?>? = null
) : Parcelable