package com.mobjoy.data.datasourceimpl

import com.google.gson.Gson
import com.mobjoy.data.datasources.CitiesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.io.BufferedReader

@Module
@InstallIn(ViewModelComponent::class)
class DI {
    @Provides
    fun provide(gson: Gson, bufferedReader: BufferedReader): CitiesDataSource {
        return CitiesDataSourceImpl(gson, bufferedReader)
    }


}