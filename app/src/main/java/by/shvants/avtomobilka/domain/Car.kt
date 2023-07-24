package by.shvants.avtomobilka.domain

import by.shvants.avtomobilka.data.model.ImageResponse
import java.time.Year

data class Car(
    val id: Int,
    val name: String,
    val brandName: String,
    val engine: String,
    val transmissionName: String,
    val year: Int,
    val image: String,
    val images: List<Image>?,
)
