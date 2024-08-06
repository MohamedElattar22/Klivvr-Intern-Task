package com.mobjoy.data.repositoryimpl

import com.mobjoy.domain.repository.CitiesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun providesRepositoryImpl(citiesRepositoryImpl: CitiesRepositoryImpl): CitiesRepository

}