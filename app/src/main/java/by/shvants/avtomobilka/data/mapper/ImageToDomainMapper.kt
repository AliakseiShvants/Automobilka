package by.shvants.avtomobilka.data.mapper

import by.shvants.avtomobilka.domain.Car
import by.shvants.avtomobilka.data.model.CarResponse
import by.shvants.avtomobilka.data.model.ImageResponse
import by.shvants.avtomobilka.domain.Image
import by.shvants.avtomobilka.utils.Mapper

class ImageToDomainMapper : Mapper<ImageResponse, Image> {
    override fun map(from: ImageResponse) = Image(
        id = from.id ?: 0,
        isPrimary = from.isPrimary ?: false,
        url = from.url.orEmpty(),
    )
}