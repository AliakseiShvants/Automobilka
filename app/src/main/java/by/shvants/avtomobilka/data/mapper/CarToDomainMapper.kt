package by.shvants.avtomobilka.data.mapper

import by.shvants.avtomobilka.domain.Car
import by.shvants.avtomobilka.data.model.CarResponse
import by.shvants.avtomobilka.utils.Mapper
import by.shvants.avtomobilka.utils.toListMapper

class CarToDomainMapper(
    private val imageToDomainMapper: ImageToDomainMapper,
) : Mapper<CarResponse, Car> {
    override fun map(from: CarResponse) = Car(
        id = from.id ?: 0,
        name = from.name.orEmpty(),
        brandName = from.brandName.orEmpty(),
        engine = from.engine.orEmpty(),
        transmissionName = from.transmissionName.orEmpty(),
        year = from.year ?: 0,
        image = from.image.orEmpty(),
        images = from.images?.let { imageToDomainMapper.toListMapper().map(it) }
    )
}