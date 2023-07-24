package by.shvants.avtomobilka.data.mapper

import by.shvants.avtomobilka.data.model.*
import by.shvants.avtomobilka.domain.*
import by.shvants.avtomobilka.utils.Mapper

class UserToDomainMapper : Mapper<UserResponse, User> {
    override fun map(from: UserResponse) = User(
        id = from.id ?: 0,
        username = from.username.orEmpty(),
        url = from.avatar?.url.orEmpty(),
    )
}