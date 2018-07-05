package com.example.eugene.rxtestproject.domain.usecase.base

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class AbsUseCaseCompl<Params> : AbsBaseUseCase() {

    abstract fun buildUseCaseObservable(@Nullable params: Params): Completable

    fun execute(@NonNull observer: DisposableCompletableObserver, params: Params) {

        val completable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        addDisposable(completable.subscribeWith(observer))
    }

    fun execute(@NonNull observer: DisposableCompletableObserver, @NonNull transformer: CompletableTransformer, @Nullable params: Params) {
        val completable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(transformer)
        addDisposable(completable.subscribeWith(observer))
    }
}
