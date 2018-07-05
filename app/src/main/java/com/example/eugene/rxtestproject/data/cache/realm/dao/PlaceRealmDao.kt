package com.example.eugene.rxtestproject.data.cache.realm.dao

import com.example.eugene.rxtestproject.data.cache.dao.PlaceDao
import com.example.eugene.rxtestproject.data.cache.realm.db.RealmDatabase
import com.example.eugene.rxtestproject.data.cache.realm.model.PlaceRealmData
import com.example.eugene.rxtestproject.data.cache.realm.rx.RealmResultsInfiniteObservable
import com.example.eugene.rxtestproject.data.mapper.DataRealmDomainMapper
import com.example.eugene.rxtestproject.domain.model.Place
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import log
import javax.inject.Inject


class PlaceRealmDao @Inject constructor() : PlaceDao {

    override fun updatePlaces(place: Place): Completable {
        return Completable.create { emitter ->

            val realmConfiguration = RealmDatabase.commonRealmConfiguration()
            val realm = Realm.getInstance(realmConfiguration)

            realm.executeTransaction {
                realm.where(PlaceRealmData::class.java)
                        .findAll()
                        .deleteAllFromRealm()
                realm.insert(PlaceRealmData())
            }
            realm.close()
            throw RuntimeException()
            emitter.onComplete()
        }

    }

    override fun getPlaces(): Observable<List<Place>> {
        val realmConfiguration = RealmDatabase.commonRealmConfiguration()
        return Observable.create(GetPlacesObservable(realmConfiguration))
                .log()
//                .compose { upstream -> upstream.observeOn(Schedulers.io()) }
                .map { realmResults ->
                    realmResults.asIterable().map { placeData -> DataRealmDomainMapper.toDomain(placeData) }
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())

////                .lift(ObservableOperator<List<Place>,List<Place>> {observer -> observer })
//                .subs
////                .log()
////                .subscribeOn(workExecutor.scheduler)
////                .unsubscribeOn(workExecutor.scheduler)
    }

    // Hot data observables

    inner class GetPlacesObservable(val realmConfiguration: RealmConfiguration) : RealmResultsInfiniteObservable<PlaceRealmData>() {
        override fun getConfiguration(): RealmConfiguration = realmConfiguration
        override fun getRealmResults(realm: Realm) = realm
                .where(PlaceRealmData::class.java)
                .findAll()
    }

}