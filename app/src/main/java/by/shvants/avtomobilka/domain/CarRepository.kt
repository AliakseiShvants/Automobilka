package by.shvants.avtomobilka.domain

import by.shvants.avtomobilka.utils.RequestResult

interface CarRepository {
    suspend fun fetchCarsFromServer(): RequestResult<List<Car>>
}