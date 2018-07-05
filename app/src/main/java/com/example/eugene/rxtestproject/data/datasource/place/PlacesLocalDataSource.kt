package com.example.eugene.rxtestproject.data.datasource.place

import com.example.eugene.rxtestproject.domain.model.Place
import io.reactivex.Completable
import io.reactivex.Observable

interface PlacesLocalDataSource {
    fun getPlaces(): Observable<List<Place>>
    fun updatePlaces(place: Place): Completable
}