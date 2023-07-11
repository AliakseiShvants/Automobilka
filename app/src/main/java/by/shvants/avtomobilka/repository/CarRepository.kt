package by.shvants.avtomobilka.repository

import by.shvants.avtomobilka.data.Car
import by.shvants.avtomobilka.data.CarToDomainMapper
import by.shvants.avtomobilka.network.CarApi
import by.shvants.avtomobilka.utils.RequestResult
import by.shvants.avtomobilka.utils.toListMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarRepository(
    private val carApi: CarApi,
    private val carToDomainMapper: CarToDomainMapper,
) {

    private var page = 1
    private var networkCache = mutableListOf<Car>()

    suspend fun fetchCarsFromServer(): RequestResult<List<Car>> {
        return withContext(Dispatchers.IO) {
            try {
                val carResponse = carApi.getCars(page++)
                val cars = carToDomainMapper.toListMapper().map(carResponse)

                networkCache.addAll(cars)

                RequestResult.Success(cars)
            } catch (e: Exception) {
                RequestResult.Error(e)
            }
        }
    }
}