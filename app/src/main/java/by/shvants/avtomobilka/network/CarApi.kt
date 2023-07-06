package by.shvants.avtomobilka.network

import retrofit2.http.GET

interface CarApi {

    @GET("/api/v1/cars/list")
    suspend fun getCars(): List<CarResponse>
}