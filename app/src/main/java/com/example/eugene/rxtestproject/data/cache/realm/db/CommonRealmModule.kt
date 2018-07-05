package com.example.eugene.rxtestproject.data.cache.realm.db

import com.example.eugene.rxtestproject.data.cache.realm.model.PlaceRealmData
import io.realm.annotations.RealmModule

@RealmModule(classes = arrayOf(
        PlaceRealmData::class
))
class CommonRealmModule