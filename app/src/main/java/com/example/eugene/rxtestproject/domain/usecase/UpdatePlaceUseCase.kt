package com.example.eugene.rxtestproject.domain.usecase

import com.example.eugene.rxtestproject.domain.model.Place
import com.example.eugene.rxtestproject.domain.repository.PlacesRepository
import com.example.eugene.rxtestproject.domain.usecase.base.AbsUseCaseCompl
import io.reactivex.Completable
import javax.inject.Inject

class UpdatePlaceUseCase @Inject constructor(private val placesRepository: PlacesRepository): AbsUseCaseCompl<UpdatePlaceUseCase.Params>() {
    override fun buildUseCaseObservable(params: Params): Completable {
        return placesRepository.updatePlaces(params.place)
    }

    class Params (val place : Place)
}