package by.shvants.avtomobilka.data.repository

import by.shvants.avtomobilka.data.mapper.CarToDomainMapper
import by.shvants.avtomobilka.data.CarApi
import by.shvants.avtomobilka.data.mapper.PostsToDomainMapper
import by.shvants.avtomobilka.domain.*
import by.shvants.avtomobilka.utils.RequestResult
import by.shvants.avtomobilka.utils.toListMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarRepositoryImpl(
    private val carApi: CarApi,
    private val carToDomainMapper: CarToDomainMapper,
) : CarRepository {

    private var carsPage = 1
    private var carsCache = mutableListOf<Car>()

    override suspend fun fetchCarsFromServer(): RequestResult<List<Car>> {
        return withContext(Dispatchers.IO) {
            try {
                val carResponse = carApi.getCars(carsPage++)
                val cars = carToDomainMapper.toListMapper().map(carResponse)

                carsCache.addAll(cars)

                RequestResult.Success(carsCache)
            } catch (e: Exception) {
                RequestResult.Error(e)
            }
        }
    }
}