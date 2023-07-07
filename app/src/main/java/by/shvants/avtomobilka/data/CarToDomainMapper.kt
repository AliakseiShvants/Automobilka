package by.shvants.avtomobilka.data

import by.shvants.avtomobilka.network.CarResponse
import by.shvants.avtomobilka.utils.Mapper

class CarToDomainMapper : Mapper<CarResponse, Car> {
    override fun map(from: CarResponse) = Car(
        id = from.id ?: 0,
//        brandName = from.brandName.orEmpty(),
//        engine = from.engine.orEmpty(),
//        isForSale = from.forSale == 1,
        image = from.image.orEmpty(),
        name = from.name.orEmpty(),
    )
}