package com.example.eugene.rxtestproject.data.repository

import com.example.eugene.rxtestproject.data.datasource.place.PlacesLocalDataSource
import com.example.eugene.rxtestproject.domain.model.Place
import com.example.eugene.rxtestproject.domain.repository.PlacesRepository
import io.reactivex.Completable
import io.reactivex.Observable
import log
import javax.inject.Inject

class PlaceDataRepository @Inject constructor(private val placesLocalDataSource: PlacesLocalDataSource): PlacesRepository {
    override fun updatePlaces(place: Place): Completable {
        return placesLocalDataSource.updatePlaces(place)
    }

    override fun getPlaces(): Observable<List<Place>> {
        return placesLocalDataSource.getPlaces().log()
    }
}