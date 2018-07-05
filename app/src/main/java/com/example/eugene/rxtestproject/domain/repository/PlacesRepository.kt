package com.example.eugene.rxtestproject.domain.repository

import com.example.eugene.rxtestproject.domain.model.Place
import io.reactivex.Completable
import io.reactivex.Observable

interface PlacesRepository {
    fun getPlaces(): Observable<List<Place>>
    fun updatePlaces(place: Place): Completable
}