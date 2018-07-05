package com.example.eugene.rxtestproject.presentation.main

import com.eway.presentation.BasePresenter
import com.example.eugene.rxtestproject.domain.model.Place
import com.example.eugene.rxtestproject.domain.usecase.GetPlaceUseCase
import com.example.eugene.rxtestproject.domain.usecase.UpdatePlaceUseCase
import com.example.eugene.rxtestproject.log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(private val getPlaceUseCase: GetPlaceUseCase,
                                        private val updatePlaceUseCase: UpdatePlaceUseCase
) : BasePresenter<MainView>() {

    override fun onViewShown() {
        super.onViewShown()
        getPlaceUseCase.buildSubscriptionUseCase(GetPlaceUseCase.Params())
                .subscribeOn(Schedulers.single())
                .unsubscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    log("[RX]", "Presenter onNext result thread ${Thread.currentThread().name}")
                }
//        getPlaceUseCase.subscribe(object : DisposableObserver<List<Place>>() {
//            override fun onComplete() {
//                log("[RX]", "Presenter onComplete result thread ${Thread.currentThread().name}")
//            }
//
//            override fun onNext(place: List<Place>) {
//                log("[RX]", "Presenter onNext result thread ${Thread.currentThread().name}")
//            }
//
//            override fun onError(e: Throwable) {
//                e.printStackTrace()
//            }
//
//        }, GetPlaceUseCase.Params())


    }

    fun togglePlacesDb() {
        updatePlaceUseCase.execute(object : DisposableCompletableObserver() {
            override fun onComplete() {
                log("[RX]", "Presenter onComplete result thread ${Thread.currentThread().name}")
            }

            override fun onError(e: Throwable) {
                log("[RX]", "Presenter onError ${e.message}")
            }
        }, UpdatePlaceUseCase.Params(Place()))
    }

    override fun onViewHidden() {
        getPlaceUseCase.clear()
        super.onViewHidden()
    }

    override fun onDestroy() {
        getPlaceUseCase.dispose()
        updatePlaceUseCase.dispose()
        super.onDestroy()
    }
}