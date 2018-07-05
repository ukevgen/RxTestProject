package com.example.eugene.rxtestproject.data.cache.dao

import com.example.eugene.rxtestproject.domain.model.Place
import io.reactivex.Completable
import io.reactivex.Observable

interface PlaceDao {
    fun getPlaces(): Observable<List<Place>>
    fun updatePlaces(place: Place): Completable
}