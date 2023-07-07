package by.shvants.avtomobilka.repository

import by.shvants.avtomobilka.data.Car
import by.shvants.avtomobilka.data.CarToDomainMapper
import by.shvants.avtomobilka.network.CarApi
import by.shvants.avtomobilka.network.CarResponse
import by.shvants.avtomobilka.utils.Result
import by.shvants.avtomobilka.utils.toListMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarRepository(
    private val carApi: CarApi,
    private val carToDomainMapper: CarToDomainMapper,
) {

    private var networkCache = mutableListOf<Car>()

    suspend fun fetchCarsFromServer(
        page: Int,
    ): Result<List<Car>> {
        return withContext(Dispatchers.IO) {
            try {
                val carResponse = carApi.getCars(page)
                val cars = carToDomainMapper.toListMapper().map(carResponse)

                networkCache.addAll(cars)

                Result.Success(cars)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}