package com.example.eugene.rxtestproject.data.cache.realm.model

import com.example.EmptyConstants.EMPTY_DOUBLE
import com.example.EmptyConstants.EMPTY_STRING
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass


@RealmClass
open class PlaceRealmData : RealmObject() {

    @PrimaryKey var id = EMPTY_STRING
    var latitude = EMPTY_DOUBLE
    var longitude = EMPTY_DOUBLE
    var image = EMPTY_STRING
    var name = EMPTY_STRING
    var address = EMPTY_STRING

}