package com.mobjoy.klivvrinternshiptask.repository

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideSampleRepository(@ApplicationContext context: Context): CitiesRepository {
        return CitiesRepository(context)
    }
}