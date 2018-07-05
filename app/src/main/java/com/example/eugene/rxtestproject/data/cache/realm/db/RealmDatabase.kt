package com.example.eugene.rxtestproject.data.cache.realm.db

import io.realm.RealmConfiguration

object RealmDatabase {

    fun commonRealmConfiguration() = RealmConfiguration.Builder().name("common.realm").modules(CommonRealmModule()).build()!!
}