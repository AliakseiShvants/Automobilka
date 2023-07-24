package by.shvants.avtomobilka.data.mapper

import by.shvants.avtomobilka.data.model.*
import by.shvants.avtomobilka.domain.*
import by.shvants.avtomobilka.utils.Mapper
import by.shvants.avtomobilka.utils.toListMapper

class PostsToDomainMapper(
    private val userToDomainMapper: UserToDomainMapper,
    private val postToDomainMapper: PostToDomainMapper,
) : Mapper<PostsResponse, Posts> {
    override fun map(from: PostsResponse) = Posts(
        user = from.user?.let { userToDomainMapper.map(it) },
        posts = from.posts?.let {
            postToDomainMapper.toListMapper().map(it)
        } ?: emptyList()
    )
}