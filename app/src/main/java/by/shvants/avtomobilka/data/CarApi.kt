package by.shvants.avtomobilka.data

import by.shvants.avtomobilka.data.model.CarResponse
import by.shvants.avtomobilka.data.model.PostsResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CarApi {

    @GET("/api/v1/cars/list")
    suspend fun getCars(
        @Query("page") page: Int,
    ): List<CarResponse>

    @GET("/api/v1/car/id")
    suspend fun getCar(
        @Field("id") id: Long,
    ): List<CarResponse>

    @GET("/api/v1/car/{id}/posts")
    suspend fun getPosts(
        @Path("id") id: Int,
        @Query("page") page: Int,
    ): PostsResponse
}