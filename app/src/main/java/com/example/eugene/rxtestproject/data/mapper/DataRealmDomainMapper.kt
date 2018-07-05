package com.example.eugene.rxtestproject.data.mapper

import com.example.eugene.rxtestproject.data.cache.realm.model.PlaceRealmData
import com.example.eugene.rxtestproject.domain.model.Place

object DataRealmDomainMapper {

    fun toDomain(placeData: PlaceRealmData): Place {
        val place = Place()
        place.image = placeData.image
        place.address = placeData.address
        place.name = placeData.name
        place.id = placeData.id
        return place
    }

}