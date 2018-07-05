package com.example.eugene.rxtestproject.data.cache

import com.example.eugene.rxtestproject.data.cache.dao.PlaceDao
import com.example.eugene.rxtestproject.data.datasource.place.PlacesLocalDataSource
import com.example.eugene.rxtestproject.domain.model.Place
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import log
import javax.inject.Inject

class PlacesLocalDataSourceImpl @Inject constructor(private val placeDao: PlaceDao): PlacesLocalDataSource {
    override fun updatePlaces(place: Place): Completable {
        return placeDao.updatePlaces(place)
                .onErrorComplete()
    }

    override fun getPlaces(): Observable<List<Place>> {
        return  placeDao.getPlaces()
    }
}