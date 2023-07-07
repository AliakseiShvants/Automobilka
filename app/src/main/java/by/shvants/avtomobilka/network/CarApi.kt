package by.shvants.avtomobilka.network

import retrofit2.http.GET
import retrofit2.http.Query

interface CarApi {

    @GET("/api/v1/cars/list")
    suspend fun getCars(
        @Query("page") page: Int = 1,
    ): List<CarResponse>
}