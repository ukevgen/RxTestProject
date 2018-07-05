package com.example.eugene.rxtestproject.domain.usecase.base

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class SubscriberUseCase<R, in P> (val scheduler: Scheduler) {

    var compositeSubscription: CompositeDisposable = CompositeDisposable()


    abstract fun buildSubscriptionUseCase( params: P): Observable<R>

    private fun getScheduledSubscription(params: P, transformer: ObservableTransformer<R, R>?): Observable<R> {
        val observable = buildSubscriptionUseCase(params)
        if (transformer != null) {
            return observable.compose(transformer)
        }
        return observable
    }

    fun subscribe(observer: DisposableObserver<R>, params: P) = subscribe(observer, ObservableTransformer { upstream ->
        upstream.subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(scheduler)
    }, params)

    fun subscribe(observer: DisposableObserver<R>, transformer: ObservableTransformer<R, R>?, params: P) {
        if (compositeSubscription.size() > 0) return
        compositeSubscription.add(getScheduledSubscription(params, transformer).subscribeWith(observer))
    }

    open fun clear() {
        compositeSubscription.clear()
    }

    open fun dispose() {
        compositeSubscription.dispose()
    }
}