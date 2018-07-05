package com.example.eugene.rxtestproject.data.cache.realm.rx


import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposables
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Listen for RealmResults changes and return whole RealmResults with all (including updated) items.
 * Could be used for listening for one object. React to changes in nested objects.
 */
abstract class RealmResultsInfiniteObservable<Obj : RealmObject> : ObservableOnSubscribe<RealmResults<Obj>> {

    @Throws(Exception::class)
    override fun subscribe(emitter: ObservableEmitter<RealmResults<Obj>>) {
        val realm = Realm.getInstance(getConfiguration())

        val results = getRealmResults(realm)

        val listener = { _: RealmResults<Obj> ->
            if (!emitter.isDisposed) {
                emitter.onNext(results)
            }
        }

        emitter.setDisposable(Disposables.fromRunnable {
            results.removeChangeListener(listener)
            realm.close()
            emitter.onComplete()
        })

        results.addChangeListener(listener)
        emitter.onNext(results)
    }

    abstract fun getConfiguration(): RealmConfiguration
    abstract fun getRealmResults(realm: Realm): RealmResults<Obj>

}
