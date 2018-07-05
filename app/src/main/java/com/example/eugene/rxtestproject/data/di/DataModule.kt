package com.example.eugene.rxtestproject.data.di

import com.example.eugene.rxtestproject.data.repository.PlaceDataRepository
import com.example.eugene.rxtestproject.domain.repository.PlacesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun providePlacesDataRepository(repository: PlaceDataRepository): PlacesRepository
}