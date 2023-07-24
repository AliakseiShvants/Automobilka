package by.shvants.avtomobilka.presentation

import by.shvants.avtomobilka.domain.Car

typealias CarsEventListener = (CarsEvent) -> Unit

sealed class CarsEvent
data class OnItemClick(val car: Car) : CarsEvent()