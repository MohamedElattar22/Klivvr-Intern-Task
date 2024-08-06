package com.mobjoy.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class CoordDto(
    val lon: @RawValue Any? = null,
    val lat: @RawValue Any? = null
) : Parcelable