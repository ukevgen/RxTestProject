package com.example.eugene.rxtestproject.data.cache.realm.dao.di

import com.example.eugene.rxtestproject.data.cache.dao.PlaceDao
import com.example.eugene.rxtestproject.data.cache.realm.dao.PlaceRealmDao
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DaoRealmModule {
    @Binds
    @Singleton
    abstract fun providePlaceDao(placeDao: PlaceRealmDao): PlaceDao
}