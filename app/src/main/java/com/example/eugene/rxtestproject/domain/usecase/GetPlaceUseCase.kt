package com.example.eugene.rxtestproject.domain.usecase

import com.example.eugene.rxtestproject.android.di.qualifier.thread.DataThread
import com.example.eugene.rxtestproject.domain.executor.IOExecutionThread
import com.example.eugene.rxtestproject.domain.model.Place
import com.example.eugene.rxtestproject.domain.repository.PlacesRepository
import com.example.eugene.rxtestproject.domain.usecase.base.SubscriberUseCase
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import log
import javax.inject.Inject

class GetPlaceUseCase @Inject constructor(private val placesRepository: PlacesRepository,
                                          private val refreshObservable: Observable<Any>,
                                          @DataThread  val ioExecutionThread: IOExecutionThread) : SubscriberUseCase<List<Place>, GetPlaceUseCase.Params>(ioExecutionThread.getDataScheduler()) {

    override fun buildSubscriptionUseCase(params: Params): Observable<List<Place>> {
        return Observable.combineLatest(
                placesRepository.getPlaces(),
                refreshObservable.observeOn(Schedulers.io()).log(),
                BiFunction { places, any -> places }
        )
    }

    class Params
}