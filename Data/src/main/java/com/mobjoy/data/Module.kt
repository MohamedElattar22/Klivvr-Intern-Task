package com.mobjoy.data

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.BufferedReader

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    fun providesBufferReader(@ApplicationContext context: Context): BufferedReader {
        return context.assets.open("cities.json")
            .bufferedReader()
    }

}