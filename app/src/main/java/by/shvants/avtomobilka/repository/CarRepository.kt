package by.shvants.avtomobilka.repository

import by.shvants.avtomobilka.network.CarApi
import by.shvants.avtomobilka.network.CarResponse
import by.shvants.avtomobilka.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CarRepository(
    private val carApi: CarApi,
) {

    suspend fun fetchCarsFromServer(): Result<List<CarResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val result = carApi.getCars()

                Result.Success(result)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}