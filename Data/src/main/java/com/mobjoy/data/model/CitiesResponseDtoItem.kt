package com.mobjoy.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CitiesResponseDtoItem(
    val country: String? = null,
    val coord: CoordDto? = null,
    val name: String? = null,
    val id: Int? = null
) : Parcelable