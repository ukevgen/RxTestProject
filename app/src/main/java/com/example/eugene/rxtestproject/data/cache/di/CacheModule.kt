package com.example.eugene.rxtestproject.data.cache.di

import com.example.eugene.rxtestproject.data.cache.PlacesLocalDataSourceImpl
import com.example.eugene.rxtestproject.data.datasource.place.PlacesLocalDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CacheModule {
    @Binds
    @Singleton
    abstract fun providePlacesLocal(placesLocalDataSource: PlacesLocalDataSourceImpl): PlacesLocalDataSource
}