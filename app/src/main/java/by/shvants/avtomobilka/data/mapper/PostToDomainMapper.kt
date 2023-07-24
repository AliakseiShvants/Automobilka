package by.shvants.avtomobilka.data.mapper

import by.shvants.avtomobilka.data.model.*
import by.shvants.avtomobilka.domain.*
import by.shvants.avtomobilka.utils.Mapper

class PostToDomainMapper(
    private val userToDomainMapper: UserToDomainMapper,
) : Mapper<PostResponse, Post> {
    override fun map(from: PostResponse) = Post(
        id = from.id ?: 0,
        author = from.author?.let { userToDomainMapper.map(it) },
        comment_count = from.comment_count ?: 0,
        created_at = from.created_at.orEmpty(),
        img = from.img.orEmpty(),
        like_count = from.like_count ?: 0,
        text = from.text.orEmpty(),
    )
}